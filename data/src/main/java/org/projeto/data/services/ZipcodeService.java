package org.projeto.data.services;

import org.projeto.data.entities.Zipcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.repositories.ZipcodeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class ZipcodeService{
  private static ZipcodeRepository zipcodeRepository;

  @Autowired
  public ZipcodeService(ZipcodeRepository zipcodeRepository){
    ZipcodeService.zipcodeRepository = zipcodeRepository;
  }

  public static List<Zipcode> getZipcodes(){ return ZipcodeService.zipcodeRepository.findAll();}

  public static void addNew(Zipcode newZipcode){
    if (ZipcodeService.zipcodeRepository.existsById(newZipcode.getId())){
      throw new IllegalStateException("Zipcode already exists");
    }
    ZipcodeService.zipcodeRepository.save(newZipcode);
  }
  public static void delete(Long id){
    boolean exists = ZipcodeService.zipcodeRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("CodPostal with id"+id+"does not exist");
    }else{
      ZipcodeService.zipcodeRepository.deleteById(id);
    }
  }

  public static void update(Long id,String district, String city, String locale){
    Zipcode zipcode = ZipcodeService.zipcodeRepository.findById(id).orElseThrow(()-> new IllegalStateException( "CodPostal with id "+ id + " does not exist! "));

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
