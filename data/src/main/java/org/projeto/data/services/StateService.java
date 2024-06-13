package org.projeto.data.services;

import org.projeto.data.entities.State;
import org.projeto.data.repositories.StageRepository;
import org.projeto.data.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StateService {
    private static StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository){ StateService.stateRepository = stateRepository;}

    public static void addNew(State newState){
        Optional<State> existingState = StateService.stateRepository.findByDescription(newState.getDescription());
        if(existingState.isPresent()) {
            throw new IllegalStateException("This state already exists");
        }else {
            StateService.stateRepository.save(newState);
        }
    }
    public static void delete(Long id){
        Optional<State> existingState = StateService.stateRepository.findById(id);
        if (existingState.isPresent()){
            StateService.stateRepository.deleteById(id);
        }else {
            throw new IllegalStateException("The state does not exist");
        }

    }
}
