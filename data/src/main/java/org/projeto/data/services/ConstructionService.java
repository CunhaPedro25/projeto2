package org.projeto.data.services;

import org.projeto.data.entities.Construction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.projeto.data.repositories.ConstructionRepository;

import java.util.List;

@Service
public class ConstructionService {
    private static ConstructionRepository constructionRepository;

    @Autowired
    public ConstructionService(ConstructionRepository constructionRepository) {ConstructionService.constructionRepository = constructionRepository;}

    public List<Construction> findByProjectID(Integer id){
        return ConstructionService.constructionRepository.findbyProjectID(id);
    }

    public List<Construction> findConstructionsByProjectAndAndState(Integer projectID, Integer stateID){
        return ConstructionService.constructionRepository.findConstructionsByProjectAndAndState(projectID, stateID);
    }

    public List<Construction> findConstructionsByTeam_Id(Integer teamID){
        return ConstructionService.constructionRepository.findConstructionsByTeam_Id(teamID);
    }
}
