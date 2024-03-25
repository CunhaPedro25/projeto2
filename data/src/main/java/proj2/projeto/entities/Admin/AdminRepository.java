package proj2.projeto.entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proj2.projeto.entities.Cliente.Cliente;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
  Admin findById(Integer id);
  Optional<Admin> findByEmailAndPassword(String email,String password);
  @Query("SELECT a FROM Admin a WHERE a.email = ?1")
  Optional<Admin> findByEmail(String email);
}
