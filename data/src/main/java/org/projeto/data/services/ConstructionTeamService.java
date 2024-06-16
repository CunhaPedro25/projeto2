package org.projeto.data.services;

import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.entities.Project;
import org.projeto.data.repositories.ConstructionTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructionTeamService {
    private static ConstructionTeamRepository constructionTeamRepository;
    @Autowired
    public ConstructionTeamService(ConstructionTeamRepository constructionTeamRepository) {
        ConstructionTeamService.constructionTeamRepository = constructionTeamRepository;
    }
    public static void addNew(ConstructionTeam newConstructionTeam){
        ConstructionTeamService.constructionTeamRepository.save(newConstructionTeam);
    }
    public static void delete(Long constructionTeamID){
        ConstructionTeamService.constructionTeamRepository.deleteById(constructionTeamID);
    }
    public static void findByID(Long id){
        ConstructionTeamService.constructionTeamRepository.findById(id);
    }
    public static void findByConstructionID(Integer id){
        ConstructionTeamService.constructionTeamRepository.findByConstruction_Id(id);
    }
    public static void findByTeamID(Integer id){
        ConstructionTeamService.constructionTeamRepository.findByTeam_Id(id);
    }
    public static void findByConstructionIDAndTeamID(Integer constructionID, Integer teamID){
        ConstructionTeamService.constructionTeamRepository.findByConstruction_IdAndTeam_Id(constructionID, teamID);
    }
}
