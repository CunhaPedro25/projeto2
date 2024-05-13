package org.projeto.data.services;

import org.projeto.data.entities.Team;
import org.projeto.data.entities.User;
import org.projeto.data.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamService {
  private final TeamRepository teamRepository;

  @Autowired
  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public List<Team> getTeams(){ return this.teamRepository.findAll();}

  public void addNew(Team newTeam){
    Optional<Team> EquipaByChefe = this.teamRepository.findByLeader_Id(newTeam.getLeader().getId());
    if (EquipaByChefe.isEmpty()){
      throw new IllegalStateException("This Equipa has no Chefe!");
    }
    this.teamRepository.save(newTeam);
  }

  public void delete(Long id){
    boolean exists = this.teamRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Equipa with id"+id+"does not exist");
    }else{
      this.teamRepository.deleteById(id);
    }
  }

  public void update(Long id, User leader){
    Team team = this.teamRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Equipa with id "+ id + " does not exist! "));

    if (leader != null && leader.getId() != null && !Objects.equals(team.getLeader(), leader)) {
      Optional<Team> EquipaOptional = this.teamRepository.findByLeader_Id(leader.getId());
      if (EquipaOptional.isPresent()){
        throw new IllegalStateException("This team already has that Chefe already exists!");
      }else {
        team.setLeader(leader);
      }
    }
  }
}
