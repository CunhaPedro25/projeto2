package proj2.projeto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import proj2.projeto.entities.*;
import proj2.projeto.entities.enums.ClientType;
import proj2.projeto.entities.enums.State;
import proj2.projeto.entities.users.Client;
import proj2.projeto.entities.users.Engineer;
import proj2.projeto.entities.users.Worker;
import proj2.projeto.repositories.*;
import proj2.projeto.repositories.documents.BudgetRepository;
import proj2.projeto.repositories.documents.ProjectRepository;
import proj2.projeto.repositories.enums.ClientTypeRepository;
import proj2.projeto.repositories.enums.StateRepository;
import proj2.projeto.repositories.users.ClientRepository;
import proj2.projeto.repositories.users.EngineerRepository;
import proj2.projeto.repositories.users.WorkerRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);

	}


	@Bean
	public CommandLineRunner commandLineRunner1(ClientRepository clientRepository, ZipcodeRepository zipcodeRepository, ClientTypeRepository clientTypeRepository,
												TeamRepository teamRepository,
												WorkerRepository workerRepository,
												MaterialRepository materialRepository,
												EngineerRepository engineerRepository,
												ProjectRepository projectRepository,
												BudgetRepository budgetRepository,
												StateRepository stateRepository,
												StageRepository stageRepository,
												StageMaterialRepository stageMaterialRepository,
												ConstructionRepository constructionRepository,
												InvoiceRepository invoiceRepository){
		return (args ->{
			var zipcode = new Zipcode("4925-595","Viana do Castelo", "Viana do Castelo", "Serreleis");
			zipcodeRepository.save(zipcode);
			var clientType = new ClientType("Lazy");
			clientTypeRepository.save(clientType);
			var client = new Client("miguel","miguel@mail.com","AHHSHSHSHS","+351123123123","rua das ruas",69,zipcode,clientType);
			clientRepository.save(client);

			zipcode = zipcodeRepository.findById("4925-595");
			clientType = clientTypeRepository.findById(1);
			client = new Client("pedro","pedro@mail.com","AHHSHSHSHS","+351321321321","rua das ruas",69,zipcode,clientType);
			clientRepository.save(client);

			var leader = new Worker("paulo", "paulo@email.com","kjsdhaisk", "+351123456789");
			workerRepository.save(leader);
			var team = new Team(leader);
			leader.setTeam(team);
			teamRepository.save(team);
			workerRepository.save(leader);

			var worker = new Worker("teixeira", "teixeira@email.com","kjsdhaisk", "+351987654321", team);
			workerRepository.save(worker);
			worker.setPhone("+351102938475");
			workerRepository.save(worker);

			var madeira = new Material("Madeira", new BigDecimal("3.50"), 100);
			var cimento = new Material("Cimento", new BigDecimal("3.50"), 100);
			var tijolos = new Material("Tijolos", new BigDecimal("3.50"), 100);
			materialRepository.save(madeira);
			materialRepository.save(cimento);
			materialRepository.save(tijolos);

			var engineer = new Engineer("Manuel", "manuel@email.com", "Aihfsdfsadfsadf", "+351010192925");
			engineerRepository.save(engineer);

			var project = new Project(client, engineer);
			projectRepository.save(project);
			var budget = new Budget(client,engineer,project);
			budgetRepository.save(budget);

			var state = new State("In progress");
			stateRepository.save(state);
			var stage = new Stage(budget, state,"Arranjar as paredes");
			stageRepository.save(stage);
			var stageMadeira = new	StageMaterial(stage, madeira, 20);
			var stageCimento = new	StageMaterial(stage, cimento, 20);
			var stageTijolos = new	StageMaterial(stage, tijolos, 20);
			stageMaterialRepository.save(stageMadeira);
			stageMaterialRepository.save(stageCimento);
			stageMaterialRepository.save(stageTijolos);

			var construction = new Construction(team, budget,stage, LocalDate.now());
			constructionRepository.save(construction);

			state = new State("Finished");
			stateRepository.save(state);
			stage.setState(state);
			stageRepository.save(stage);
		});
	}

}
