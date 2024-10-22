package org.projeto.data.services;

import org.projeto.data.entities.Material;
import org.projeto.data.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Material existingMaterial = MaterialService.materialRepository.findMaterialByName(newMaterial.getName());
        if (existingMaterial != null){
            throw new IllegalStateException("O material ja existe");
        }else {
            MaterialService.materialRepository.save(newMaterial);
        }
    }
    public static void delete(Long id){
        MaterialService.materialRepository.deleteById(id);
    }

    public static void update(Material editMaterial) {
        MaterialService.materialRepository.save(editMaterial);
    }

    public static List<Material> getAllMaterials() {
        return MaterialService.materialRepository.findAll();
    }
    public static List<String> getAllMaterialsNames(){
        List<String> names = new ArrayList<>();
        for (Material material : MaterialService.materialRepository.findAll()){
            names.add(material.getName());
        }
        return names;
    }
    public static Material getMaterialByName(String name){
        return MaterialService.materialRepository.findMaterialByName(name);
    }
}
