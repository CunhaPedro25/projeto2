package org.projeto.data.services;

import org.projeto.data.entities.Complaint;
import org.projeto.data.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {
    private static ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) { ComplaintService.complaintRepository = complaintRepository;}

    public List<Complaint> findComplaintsByClientID (Integer id){
        return ComplaintService.complaintRepository.findComplaintsByClient_Id(id);
    }
    public List<Complaint> findComplaintByConstructionID (Integer id){
        return ComplaintService.complaintRepository.findComplaintByConstruction_Id(id);
    }
    public void addNew(Complaint newComplaint){
        ComplaintService.complaintRepository.save(newComplaint);
    }
    public void delete (Long id){
        ComplaintService.complaintRepository.deleteById(id);
    }

}
