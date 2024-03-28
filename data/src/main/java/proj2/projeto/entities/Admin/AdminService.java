package proj2.projeto.entities.Admin;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Admin.Admin;
import proj2.projeto.entities.Admin.AdminRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class AdminService {
  private final AdminRepository AdminRepository;
  @Autowired
  public AdminService(AdminRepository AdminRepository){ this.AdminRepository = AdminRepository;}
  public List<Admin> getAdmins(){ return AdminRepository.findAll();}

  public void addNew(Admin newAdmin){
    Optional<Admin> AdminByEmail = AdminRepository.findByEmail(newAdmin.getEmail());
    if (AdminByEmail.isPresent()){
      throw new IllegalStateException("email taken");
    }
    AdminRepository.save(newAdmin);
  }
  public void delete(Long AdminId){
    boolean exists = AdminRepository.existsById(AdminId);
    if (!exists){
      throw new IllegalStateException("Admin with id"+AdminId+"does not exist");
    }else{
      AdminRepository.deleteById(AdminId);
    }
  }
  @Transactional
  public void update(Long id,String name, String email,String password){
    Admin Admin = AdminRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Admin with id "+ id + " does not exist! "));

    if (name != null && !name.isEmpty() && !Objects.equals(Admin.getNome(), name)){
      Admin.setNome(name);
    }
    if (password != null && !password.isEmpty() && !Objects.equals(Admin.getPassword(), password)){
      Admin.setPassword(password);
    }
    if (email != null && !email.isEmpty() && !Objects.equals(Admin.getEmail(),email)) {
      Optional<Admin> AdminOptional = AdminRepository.findByEmail(email);
      if (AdminOptional.isPresent()){
        throw new IllegalStateException("email taken");
      }else {
        Admin.setEmail(email);
      }
    }
  }
}
