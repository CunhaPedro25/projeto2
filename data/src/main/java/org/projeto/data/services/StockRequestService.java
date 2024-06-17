package org.projeto.data.services;

import org.projeto.data.entities.ConstructionTeam;
import org.projeto.data.entities.StockRequest;
import org.projeto.data.repositories.StockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StockRequestService {
    private static StockRequestRepository stockRequestRepository;

    @Autowired
    public StockRequestService(StockRequestRepository stockRequestRepository){ StockRequestService.stockRequestRepository = stockRequestRepository;}

    public static void addNew(StockRequest newState){
        StockRequestService.stockRequestRepository.save(newState);
    }

    public static List<StockRequest> getAll() {
        return StockRequestService.stockRequestRepository.findAll();
    }

    public static List<StockRequest> getByConstructionId(Integer id) {
        List<StockRequest> requests = StockRequestService.stockRequestRepository.findAll();
        List<StockRequest> requestsByConstruction = new ArrayList<>();
        for (StockRequest request : requests) {
            ConstructionTeam constructionTeam = request.getConstructionTeam();
            if (Objects.equals(constructionTeam.getConstruction().getId(), id)) {
                requestsByConstruction.add(request);
            }
        }
        return requestsByConstruction;
    }
}
