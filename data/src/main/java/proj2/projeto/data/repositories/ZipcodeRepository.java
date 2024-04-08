package proj2.projeto.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proj2.projeto.data.entities.Zipcode;

import java.util.List;

@Repository
public interface ZipcodeRepository  extends JpaRepository<Zipcode, Long> {

  Zipcode findById(String id);
  boolean existsById(String id);
  List<Zipcode> findZipcodesByDistrict(String district);
  List<Zipcode> findZipcodesByCity(String city);
  List<Zipcode> findZipcodesByLocale(String locale);
}
