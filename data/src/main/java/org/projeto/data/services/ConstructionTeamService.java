package org.projeto.data.services;

import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.repositories.ConstructionTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConstructionTeamService {
    private static ConstructionTeamRepository constructionTeamRepository;

    @Autowired
    public ConstructionTeamService(ConstructionTeamRepository constructionTeamRepository) {
        ConstructionTeamService.constructionTeamRepository = constructionTeamRepository;
    }

    public static ConstructionTeam addNew(ConstructionTeam newConstructionTeam){
        return ConstructionTeamService.constructionTeamRepository.save(newConstructionTeam);
    }

    public static void delete(Long constructionTeamID){
        ConstructionTeamService.constructionTeamRepository.deleteById(constructionTeamID);
    }

    public static ConstructionTeam findByID(Long id){
        Optional<ConstructionTeam> constructionTeam = ConstructionTeamService.constructionTeamRepository.findById(id);
        return constructionTeam.orElse(null);
    }

    public static List<ConstructionTeam> findByConstructionID(Integer id){
        return ConstructionTeamService.constructionTeamRepository.findByConstruction_Id(id);
    }

    public static List<ConstructionTeam> findByTeamID(Integer id){
        return ConstructionTeamService.constructionTeamRepository.findByTeam_Id(id);
    }

    public static List<ConstructionTeam> findByConstructionIDAndTeamID(Integer constructionID, Integer teamID){
        return ConstructionTeamService.constructionTeamRepository.findByConstruction_IdAndTeam_Id(constructionID, teamID);
    }
}
