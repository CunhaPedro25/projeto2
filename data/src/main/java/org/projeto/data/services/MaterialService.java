package org.projeto.data.services;

import org.projeto.data.entities.Material;
import org.projeto.data.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private static MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository ){ MaterialService.materialRepository = materialRepository;}

    public static Material getMaterialByID (Integer ID){
        return MaterialService.materialRepository.findById(ID);
    }

    public static void addNew(Material newMaterial ){
        Optional<Material> existingMaterial = MaterialService.materialRepository.findMaterialByName(newMaterial.getName());
        if (existingMaterial.isPresent()){
            throw new IllegalStateException("O material ja existe");
        }else {
            MaterialService.materialRepository.save(newMaterial);
        }
    }
    public static void delete(Long id){
        MaterialService.materialRepository.deleteById(id);
    }
}
