package proj2.projeto.entities.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff extends User{
  public Staff() {}

  public Staff(String name, String email, String password, String phone) {
    super(name, email, password, phone);
  }
}