package org.projeto.data.services;

import org.projeto.data.entities.Construction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.repositories.ConstructionRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstructionService {
    private static ConstructionRepository constructionRepository;

    @Autowired
    public ConstructionService(ConstructionRepository constructionRepository) {ConstructionService.constructionRepository = constructionRepository;}

    public static List<Construction> findByProjectID(Integer id){
        return ConstructionService.constructionRepository.findByProject_Id(id);
    }

    public static List<Construction> findConstructionsByProjectAndAndState(Integer projectID, Integer stateID){
        return ConstructionService.constructionRepository.findConstructionsByProject_IdAndState_Id(projectID, stateID);
    }
    public static List<Construction> getAllConstructions(){
        return ConstructionService.constructionRepository.findAll();
    }

    public static void addNew(Construction newConstruction){
        ConstructionService.constructionRepository.save(newConstruction);
    }
    public static void delete(Long constructionID){
        Optional<Construction> existingCosntruction = ConstructionService.constructionRepository.findById(constructionID);
        if (existingCosntruction.isPresent()){
            ConstructionService.constructionRepository.deleteById(constructionID);
        }else {
            throw new IllegalStateException("That construction does not exist");
        }
    }

    public static Construction findById(Integer id) {
        return ConstructionService.constructionRepository.findById(id);
    }

    public static void update(Construction editConstruction) {
        LocalDateTime localDateTime = LocalDate.now().atStartOfDay();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        editConstruction.setLastUpdate(instant);
        ConstructionService.constructionRepository.save(editConstruction);
    }

    public static List<Integer> getAllConstructionIds() {
        return ConstructionService.constructionRepository.findAll()
                .stream()
                .map(Construction::getId)
                .collect(Collectors.toList());
    }

    public static List<String> getAllConstructionNames() {
        return ConstructionService.constructionRepository.findAll()
                .stream()
                .map(Construction::getName)
                .collect(Collectors.toList());
    }

    public static Construction findByName(String value) {
        return ConstructionService.constructionRepository.findByName(value);
    }
}
