package org.projeto.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.projeto.data.entities.Team;
import org.projeto.data.entities.users.Worker;
import org.projeto.data.repositories.TeamRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamService {
  @Autowired
  private static TeamRepository teamRepository;

  public static List<Team> getTeams(){ return teamRepository.findAll();}
  public static void addNew(Team newTeam){
    Optional<Team> EquipaByChefe = teamRepository.findByLeader_Id(newTeam.getLeader().getId());
    if (EquipaByChefe.isEmpty()){
      throw new IllegalStateException("This Equipa has no Chefe!");
    }
    teamRepository.save(newTeam);
  }
  public static void delete(Long id){
    boolean exists = teamRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Equipa with id"+id+"does not exist");
    }else{
      teamRepository.deleteById(id);
    }
  }

  public static void update(Long id, Worker leader){
    Team team = teamRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Equipa with id "+ id + " does not exist! "));

    if (leader != null && leader.getId() != null && !Objects.equals(team.getLeader(), leader)) {
      Optional<Team> EquipaOptional = teamRepository.findByLeader_Id(leader.getId());
      if (EquipaOptional.isPresent()){
        throw new IllegalStateException("This team already has that Chefe already exists!");
      }else {
        team.setLeader(leader);
      }
    }
  }
}