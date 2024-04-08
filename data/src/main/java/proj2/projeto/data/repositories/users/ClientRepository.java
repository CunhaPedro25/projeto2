package proj2.projeto.data.repositories.users;

import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.users.Client;

import java.util.List;

@Repository
public interface ClientRepository extends UserRepository<Client>{
  List<Client> findClientsByZipcode_Id(String zipcode);
}
