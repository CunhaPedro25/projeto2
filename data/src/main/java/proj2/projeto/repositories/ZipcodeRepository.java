package proj2.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.entities.Zipcode;

import java.util.List;

@Repository
public interface ZipcodeRepository  extends JpaRepository<Zipcode, Long> {
  Zipcode findById(String id);

  List<Zipcode> findZipcodesByDistrict(String district);
  List<Zipcode> findZipcodesByCity(String city);
  List<Zipcode> findZipcodesByLocale(String locale);
}
