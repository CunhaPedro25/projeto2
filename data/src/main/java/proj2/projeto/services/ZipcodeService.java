package proj2.projeto.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj2.projeto.entities.Zipcode;
import proj2.projeto.repositories.ZipcodeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ZipcodeService {
  private final ZipcodeRepository zipcodeRepository;
  @Autowired
  public ZipcodeService(ZipcodeRepository zipcodeRepository){ this.zipcodeRepository = zipcodeRepository;}
  public List<Zipcode> getZipcodes(){ return zipcodeRepository.findAll();}

  public void addNew(Zipcode newZipcode){
    if (zipcodeRepository.existsById(newZipcode.getId())){
      throw new IllegalStateException("Zipcode already exists");
    }
    zipcodeRepository.save(newZipcode);
  }
  public void delete(Long id){
    boolean exists = zipcodeRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("CodPostal with id"+id+"does not exist");
    }else{
      zipcodeRepository.deleteById(id);
    }
  }
  @Transactional
  public void update(Long id,String district, String city, String locale){
    Zipcode zipcode = zipcodeRepository.findById(id).orElseThrow(()-> new IllegalStateException( "CodPostal with id "+ id + " does not exist! "));

    if (district != null && !district.isEmpty() && !Objects.equals(zipcode.getDistrict(), district)) {
        zipcode.setDistrict(district);
    }

    if (city != null && !city.isEmpty() && !Objects.equals(zipcode.getCity(), city)) {
      zipcode.setCity(city);
    }

    if (locale != null && !locale.isEmpty() && !Objects.equals(zipcode.getLocale(), locale)) {
      zipcode.setLocale(locale);
    }
  }
}
