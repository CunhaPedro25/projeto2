package org.projeto.data.services;

import org.projeto.data.entities.ConstructionType;
import org.projeto.data.repositories.ConstructionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionTypeService {
    private static ConstructionTypeRepository constructionTypeRepository;

    @Autowired
    public ConstructionTypeService(ConstructionTypeRepository constructionTypeRepository) { ConstructionTypeService.constructionTypeRepository = constructionTypeRepository;}

    public static List<ConstructionType> getAllConstructionTypes(){
       return constructionTypeRepository.findAll();
    }
    public static void addNew(ConstructionType newType){
        Optional<ConstructionType> existingConstructionType = ConstructionTypeService.constructionTypeRepository.findByType(newType.getType());
        if (existingConstructionType.isPresent()){
            throw new IllegalStateException("Construction type already exists");
        }else {
            ConstructionTypeService.constructionTypeRepository.save(newType);
        }
    }
    public static void delete (Long constructionTypeID){
        Optional<ConstructionType> existingConstructionType = ConstructionTypeService.constructionTypeRepository.findById(constructionTypeID);
        if (existingConstructionType.isPresent()){
            ConstructionTypeService.constructionTypeRepository.deleteById(constructionTypeID);
        }else {
            throw new IllegalStateException("That Construction type does not exist");
        }
    }
}
