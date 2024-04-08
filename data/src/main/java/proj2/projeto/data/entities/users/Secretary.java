package proj2.projeto.data.entities.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "secretary")
public class Secretary extends User{
  public Secretary() {}

  public Secretary(String name, String email, String password, String phone) {
    super(name, email, password, phone);
  }
}