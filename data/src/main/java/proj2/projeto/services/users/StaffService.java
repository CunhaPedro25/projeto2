package proj2.projeto.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.users.Staff;
import proj2.projeto.repositories.users.StaffRepository;

@Service
public class StaffService extends UserService<Staff> {
  private final StaffRepository staffRepository;
  @Autowired
  public StaffService(StaffRepository staffRepository) {
    super(staffRepository);
    this.staffRepository = staffRepository;
  }
}
