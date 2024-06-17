package org.projeto.data.services;

import org.projeto.data.entities.Team;
import org.projeto.data.entities.User;
import org.projeto.data.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
  private static TeamRepository teamRepository;

  @Autowired
  public TeamService(TeamRepository teamRepository) {
    TeamService.teamRepository = teamRepository;
  }

  public static List<Team> getTeams(){ return TeamService.teamRepository.findAll();}
  public static Team getTeamById(Long id) {
    Optional<Team> teamOptional = TeamService.teamRepository.findById(id);
    if (teamOptional.isPresent()) {
      return teamOptional.get();
    } else {
      throw new IllegalStateException("Team with id " + id + " does not exist");
    }
  }

  public static void addNew(Team newTeam){

    TeamService.teamRepository.save(newTeam);
  }

  public static void delete(Long id){
    boolean exists = TeamService.teamRepository.existsById(id);
    if (!exists){
      throw new IllegalStateException("Equipa with id"+id+"does not exist");
    }else{
      TeamService.teamRepository.deleteById(id);
    }
  }

  public static void update(Long id, User leader){
    Team team = TeamService.teamRepository.findById(id).orElseThrow(()-> new IllegalStateException( "Equipa with id "+ id + " does not exist! "));

    if (leader != null && leader.getId() != null && !Objects.equals(team.getLeader(), leader)) {
      Optional<Team> EquipaOptional = TeamService.teamRepository.findByLeader_Id(leader.getId());
      if (EquipaOptional.isPresent()){
        throw new IllegalStateException("This team already has that Chefe already exists!");
      }else {
        team.setLeader(leader);
      }
    }
  }

    public static List<Integer> getAllTeamsIds() {
        List<Team> team_entities = teamRepository.findAll();
        List<Integer> team_ids = new ArrayList<>();
        for (Team team : team_entities) {
            team_ids.add(team.getId());
        }
        return team_ids;
    }

  public static void update(Team editTeam) {
    teamRepository.save(editTeam);

  }

  public static User getLeaderByTeamId(Long id) {
    Optional<Team> teamOptional = teamRepository.findById(id);
    if (teamOptional.isPresent()) {
      return teamOptional.get().getLeader();
    } else {
      throw new IllegalStateException("Team with id " + id + " does not exist");
    }
  }

  public static List<String> getLeaderNames() {
    return teamRepository.findAll().stream()
            .map(team -> team.getLeader().getName())
            .distinct()
            .collect(Collectors.toList());
  }

  public static void updateTeam(Team editTeam) {
    teamRepository.save(editTeam);
  }
}
