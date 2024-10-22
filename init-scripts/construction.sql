create table state
(
	id integer generated by default as identity
		constraint state_pk
			primary key,
	description text
);

create table construction_type
(
    id integer generated by default as identity
        constraint construction_type_pk
            primary key,
    type varchar
);

create table material
(
    id integer generated by default as identity
        constraint material_pk
            primary key,
    name varchar not null,
    value_unit decimal not null,
    quantity integer not null
);

create table user_type
(
    id integer generated by default as identity
        constraint client_type_pk
            primary key,
    type varchar not null
);

create table zipcode
(
    id varchar not null
        constraint zipcode_pk
            primary key,
    district varchar not null,
    city varchar not null,
    locale varchar not null
);

create table "user"
(
    id integer generated by default as identity
        constraint client_pk
            primary key,
    name varchar(70),
    email varchar(254) not null
        constraint email_uk
            unique,
    password varchar not null,
    phone varchar not null
        constraint phone_uk
            unique,
    address text,
    door integer,
    zipcode varchar
        constraint client_zipcode_zipcode_fk
            references zipcode,
    user_type integer not null
        constraint user_type_fk
            references user_type,
    team integer null,
    active boolean default true
);

create table team
(
    id integer generated by default as identity
        constraint team_pk
            primary key,
    busy boolean default false,
    leader integer null
        constraint leader_id_fk
            references "user",
    daily_value decimal not null
);

alter table "user"
    add constraint team_id_fk
        foreign key (team) references team;

create table project
(
    id integer generated by default as identity
        constraint projects_pk
            primary key,
    client integer not null
        constraint client_id_fk
            references "user",
    engineer integer
        constraint engineer_id_fk
            references "user",
    construction_type integer not null
        constraint construction_type_fk
            references construction_type,
    requirements_create_date date default current_date,
    budget_create_date date null,
    requirements_document varchar null,
    budget_document varchar null,
    budget decimal null,
    requirements_state bool null,
    budget_state bool null
);

create table stage
(
    id integer generated by default as identity
        constraint stage_pk
            primary key,
    name varchar,
    percentage float,
    construction_type integer not null
        constraint construction_type_fk
            references construction_type
);

create table if not exists construction
(
    id integer generated by default as identity
        constraint construction_pk
            primary key,
    project integer not null
        constraint project_id_fk
            references project,
    stage integer
        constraint stage_id_fk
            references stage,
    name varchar,
    stage_budget decimal,
    total decimal,
    state integer
        constraint state_id_fk
            references state,
    last_update timestamp default CURRENT_TIMESTAMP
);


create table construction_material
(
    construction integer not null
        constraint construction_id_fk
            references construction,
    material integer not null
        constraint material_id_fk
            references material,
    quantity integer not null,
    constraint construction_material_pk
        primary key (material, construction)
);

create table if not exists construction_team
(
    id integer generated by default as identity
        constraint construction_team_pk
            primary key,
    team integer
        constraint team_id_fk
            references team,
    construction integer
        constraint construction_id_fk
            references construction,
    start_date date,
    end_date date,
    days integer,
    daily_value decimal
);

create table complaint
(
    id integer generated by default as identity
        constraint complaint_pk
            primary key,
    client integer not null
        constraint client_id_fk
            references "user",
    construction integer not null
        constraint construction_id_fk
            references construction,
    description text not null
);

create table invoice
(
    id integer generated by default as identity
        constraint invoice_pk
            primary key,
    project integer not null
        constraint project_id_fk
            references project,
    stage integer not null
        constraint invoice_stage_id_fk
            references stage,
    client integer not null
        constraint client_id_fk
            references "user",
    stage_total decimal,
    budget_total decimal,
    percentage float,
    issue_date date default CURRENT_DATE,
    paid boolean default false
);

create table stock_request
(
    id integer generated by default as identity
        constraint stock_request_pk
            primary key,
    construction_team integer not null
        constraint construction_team_id_fk
            references construction_team,
    material integer not null
        constraint material_id_fk
            references material,
    quantity integer not null,
    request_date date default CURRENT_DATE,
    accepted boolean
);

CREATE OR REPLACE FUNCTION set_budget_create_date() RETURNS TRIGGER AS $$
BEGIN
    -- Check if the budget is being updated from NULL to a non-NULL value
    IF NEW.budget IS NOT NULL AND OLD.budget IS NULL THEN
        -- Set the budget_create_date to the current date
        NEW.budget_create_date := CURRENT_DATE;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER project_budget_update
    BEFORE UPDATE ON project
    FOR EACH ROW
EXECUTE FUNCTION set_budget_create_date();

CREATE OR REPLACE FUNCTION calculate_days() RETURNS TRIGGER AS $$
DECLARE
    team_record record;
    construction_team_record record;
BEGIN
    SELECT * INTO team_record
    FROM team
    WHERE id = NEW.team;

    UPDATE construction_team
    SET days = NEW.end_date - NEW.start_date,
        daily_value = team_record.daily_value
    WHERE id = NEW.id;

    SELECT * INTO construction_team_record
    FROM construction_team
    WHERE id = NEW.id;

    UPDATE construction
    SET total = total + team_record.daily_value * construction_team_record.days
    WHERE id = construction_team_record.construction;

    -- Set the team to not busy
    UPDATE team
    SET busy = FALSE
    WHERE id = NEW.team;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

ALTER FUNCTION calculate_days() OWNER TO postgres;

CREATE TRIGGER end_date_update
    AFTER UPDATE OF end_date ON construction_team
    FOR EACH ROW
EXECUTE FUNCTION calculate_days();

create function create_invoices() returns trigger as $$
DECLARE
    stage_row RECORD;
BEGIN
    FOR stage_row IN
        SELECT id, percentage
        FROM stage
        WHERE construction_type = NEW.construction_type
        LOOP
            INSERT INTO invoice (project, stage, client, stage_total, budget_total, percentage, issue_date, paid)
            VALUES (NEW.id, stage_row.id, NEW.client, NEW.budget * stage_row.percentage, NEW.budget, stage_row.percentage, CURRENT_DATE, FALSE);
        END LOOP;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

alter function create_invoices() owner to postgres;

CREATE TRIGGER budget_accepted_update
AFTER UPDATE ON project
FOR EACH ROW
WHEN (NEW.budget IS NOT NULL AND NEW.budget_state = TRUE)
EXECUTE FUNCTION create_invoices();

CREATE OR REPLACE FUNCTION set_requirements_create_date() RETURNS TRIGGER AS $$
BEGIN
    IF NEW.requirements_create_date IS NULL THEN
        NEW.requirements_create_date := CURRENT_DATE;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER project_insert
    BEFORE INSERT ON project
    FOR EACH ROW
EXECUTE FUNCTION set_requirements_create_date();


CREATE OR REPLACE FUNCTION set_construction_defaults() RETURNS TRIGGER AS $$
DECLARE
    first_state INTEGER;
    first_stage INTEGER;
    project_budget DECIMAL;
    type INTEGER;
BEGIN
    -- Get the first state
    SELECT id INTO first_state FROM state ORDER BY id LIMIT 1;

    -- Get the construction_type from the project table
    SELECT construction_type INTO type FROM project WHERE id = NEW.project;

    -- Get the first stage of the construction type
    SELECT id INTO first_stage FROM stage s WHERE s.construction_type = type ORDER BY id LIMIT 1;

    -- Get the budget from the project table
    SELECT budget INTO project_budget FROM project WHERE id = NEW.project;

    -- Set the state, stage and stageBudget of the new construction
    NEW.state := first_state;
    NEW.stage := first_stage;
    NEW.stage_budget := project_budget;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER construction_insert
    BEFORE INSERT ON construction
    FOR EACH ROW
EXECUTE FUNCTION set_construction_defaults();



CREATE OR REPLACE FUNCTION update_construction_total() RETURNS TRIGGER AS $$
DECLARE
    total_materials DECIMAL;
BEGIN
    -- Calculate the sum of all materials for the construction
    SELECT SUM(m.value_unit * cm.quantity) INTO total_materials
    FROM material m
             JOIN construction_material cm ON m.id = cm.material
    WHERE cm.construction = NEW.construction;

    -- Update the total of the construction
    UPDATE construction
    SET total = total_materials
    WHERE id = NEW.construction;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER construction_material_insert_update_delete
    AFTER INSERT OR UPDATE OR DELETE ON construction_material
    FOR EACH ROW
EXECUTE FUNCTION update_construction_total();



CREATE OR REPLACE FUNCTION construction_team_insert() RETURNS TRIGGER AS $$
DECLARE
    team_daily_value DECIMAL;
BEGIN
    -- Get the daily_value of the team
    SELECT daily_value INTO team_daily_value FROM team WHERE id = NEW.team;

    -- Set the team to busy
    UPDATE team
    SET busy = TRUE
    WHERE id = NEW.team;

    -- Update the daily_value and start_date of the construction_team
    UPDATE construction_team
    SET daily_value = team_daily_value
    WHERE id = NEW.id;

    if NEW.start_date IS NULL THEN
        UPDATE construction_team
        SET start_date = CURRENT_DATE
        WHERE id = NEW.id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER construction_team_insert
    AFTER INSERT ON construction_team
    FOR EACH ROW
EXECUTE FUNCTION construction_team_insert();

-- Insert dummy data for state
INSERT INTO state (description) VALUES
                                    ('Planning'),
                                    ('In Progress'),
                                    ('Completed'),
                                    ('On Hold');

-- Insert dummy data for construction_type
INSERT INTO construction_type (type) VALUES
                                         ('Residential'),
                                         ('Commercial'),
                                         ('Industrial');

-- Insert dummy data for material
INSERT INTO material (name, value_unit, quantity) VALUES
                                                      ('Concrete', 100.0, 500),
                                                      ('Steel', 150.0, 300),
                                                      ('Wood', 50.0, 1000);

-- Insert dummy data for user_type
INSERT INTO user_type (type) VALUES
                                 ('Client'),
                                 ('Engineer'),
                                 ('Worker'),
                                 ('Secretary'),
                                 ('Admin');

-- Insert dummy data for zipcode
INSERT INTO zipcode (id, district, city, locale) VALUES
                                                     ('4925-595', 'Viana do Castelo', 'Viana do Castelo', 'Serreleis'),
                                                     ('4925-582', 'Viana do Castelo', 'Viana do Castelo', 'Perre');

-- Insert dummy data for team
INSERT INTO team (busy, leader, daily_value) VALUES
                                                 (FALSE, NULL, 500.0),
                                                 (FALSE, NULL, 600.0),
                                                 (FALSE, NULL, 600.0);

-- Insert dummy data for user
INSERT INTO "user" (name, email, password, phone, address, door, zipcode, user_type, team, active) VALUES
                                                                                                       ('John Doe', 'client@email.com', '948fe603f61dc036b5c596dc09fe3ce3f3d30dc90f024c85f3c82db2ccab679d', '123-456-7890', '123 Main St', 10, '4925-595', 1, NULL, TRUE),
                                                                                                       ('Jane Smith', 'engineer@email.com', '7826b958b79c70626801b880405eb5111557dadceb2fee2b1ed69a18eed0c6dc', '098-765-4321', '456 Elm St', 7, '4925-595', 2, NULL, TRUE),
                                                                                                       ('Michael First', 'worker@email.com', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', '098-765-4322', '456 Elm St', 3, '4925-582', 3, NULL, TRUE),
                                                                                                       ('Michael Second', 'worker2@email.com', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', '098-765-4323', '456 Elm St', 3, '4925-582', 3, NULL, TRUE),
                                                                                                       ('Michael Third', 'worker3@email.com', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', '098-765-4324', '456 Elm St', 3, '4925-582', 3, NULL, TRUE),
                                                                                                       ('Michael Fourth', 'worker4@email.com', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', '098-765-4325', '456 Elm St', 3, '4925-582', 3, NULL, TRUE),
                                                                                                       ('Michael Fifth', 'worker5@email.com', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', '098-765-4326', '456 Elm St', 3, '4925-582', 3, NULL, TRUE),
                                                                                                       ('Michael Seventh', 'worker7@email.com', '87eba76e7f3164534045ba922e7770fb58bbd14ad732bbf5ba6f11cc56989e6e', '098-765-4327', '456 Elm St', 3, '4925-582', 3, NULL, TRUE),
                                                                                                       ('Maurice james', 'secretary@email.com', 'a8148532caf684760a38c6e5100fe4742cbe0c0030df36ad74a71abbad4d8369', '111-222-3332', '789 Oak St', 12, '4925-595', 4, NULL, TRUE),
                                                                                                       ('Admin User', 'admin@email.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '111-222-3333', '789 Oak St', 12, '4925-595', 5, NULL, TRUE);

-- Update team with leader ids
UPDATE team SET leader = 3 WHERE id = 1;
UPDATE team SET leader = 5 WHERE id = 2;
UPDATE team SET leader = 7 WHERE id = 3;

UPDATE "user" SET team = 1 WHERE id = 3;
UPDATE "user" SET team = 1 WHERE id = 4;
UPDATE "user" SET team = 2 WHERE id = 5;
UPDATE "user" SET team = 2 WHERE id = 6;
UPDATE "user" SET team = 3 WHERE id = 7;
UPDATE "user" SET team = 3 WHERE id = 8;


-- Insert dummy data for stage
INSERT INTO stage (name, percentage, construction_type) VALUES
                                                            ('Roofing', 0.2, 1),
                                                            ('Insulation', 0.3, 1),
                                                            ('Painting', 0.5, 1),
                                                            ('Foundation', 0.2, 2),
                                                            ('Framing', 0.2, 2),
                                                            ('Plumbing', 0.6, 2);

-- Insert dummy data for project
INSERT INTO project (client, engineer, construction_type, requirements_document, requirements_state) VALUES
                                                                                          (1, 2, 2, 'req_doc_1.pdf', FALSE),
                                                                                          (1, 2, 2, 'req_doc_2.pdf', TRUE),
                                                                                          (1, 2, 1, 'req_doc_3.pdf', TRUE),
                                                                                          (1, 2, 1, 'req_doc_4.pdf', TRUE),
                                                                                          (1, 2, 1, 'req_doc_5.pdf', TRUE);

UPDATE project
SET budget = 10000.0,
    budget_document = 'bud_doc_1.pdf'
WHERE id = 2;

UPDATE project
SET budget_state = TRUE
WHERE id = 2;

UPDATE project
SET budget = 20000.0,
    budget_document = 'bud_doc_2.pdf'
WHERE id = 3;

UPDATE project
SET budget_state = TRUE
WHERE id = 3;

UPDATE project
SET budget = 20000.0,
    budget_document = 'bud_doc_3.pdf'
WHERE id = 4;

UPDATE project
SET budget_state = FALSE
WHERE id = 4;

UPDATE project
SET budget = 20000.0,
    budget_document = 'bud_doc_4.pdf'
WHERE id = 5;

-- Insert dummy data for construction
INSERT INTO construction (project, stage, name, state) VALUES
                                                              (2, 1, 'Coral', 1),
                                                              (3, 2, 'Vizinho', 2);

-- Insert dummy data for construction_material
INSERT INTO construction_material (construction, material, quantity) VALUES
                                                                         (1, 1, 10),
                                                                         (1, 2, 20),
                                                                         (2, 3, 5);

-- Insert dummy data for construction_team
INSERT INTO construction_team (team, construction) VALUES
                                                (1, 1),
                                                (2, 2);

-- Insert dummy data for complaint
INSERT INTO complaint (client, construction, description) VALUES
                                                              (1, 1, 'Delay in construction.'),
                                                              (1, 2, 'Quality issues.');
