package org.projeto.data.services;

import org.projeto.data.entities.ConstructionMaterial;
import org.projeto.data.repositories.ConstructionMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructionMaterialService {
    private static ConstructionMaterialRepository constructionMaterialRepository;
    @Autowired
    public ConstructionMaterialService(ConstructionMaterialRepository constructionMaterialRepository){
        ConstructionMaterialService.constructionMaterialRepository = constructionMaterialRepository;
    }
    public static void delete(Long constructionMaterialID){
        ConstructionMaterialService.constructionMaterialRepository.deleteById(constructionMaterialID);
    }
    public static void update(ConstructionMaterial constructionMaterial){
        ConstructionMaterialService.constructionMaterialRepository.save(constructionMaterial);
    }
    public static void addNew(ConstructionMaterial newConstructionMaterial){
        ConstructionMaterialService.constructionMaterialRepository.save(newConstructionMaterial);
    }
    public static ConstructionMaterial getConstructionMaterialById(Long constructionMaterialID){
        return ConstructionMaterialService.constructionMaterialRepository.findById(constructionMaterialID).orElse(null);
    }

}
