package com.sapura.timesheet.timesheet_management.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sapura.timesheet.timesheet_management.dto.StatusDTO;
import com.sapura.timesheet.timesheet_management.model.Status;
import com.sapura.timesheet.timesheet_management.repository.StatusRepository;
import com.sapura.timesheet.timesheet_management.services.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    //Convert entity to DTO
    private StatusDTO convertToDTO(Status status) {
        return new StatusDTO(status.getId(), status.getStatusName());
    }
    // //Convert DTO to Entity 
    // private Status convertToEntity(StatusDTO statusDTO) {
    //     Status status = new Status();
    //     status.setStatusName(statusDTO.getStatusName());
    //     return status;
    // }

    @Override
    public List<StatusDTO> getAllStatus(){
        List<Status> statuses = statusRepository.findAll();
        return statuses.stream()
            .map(this::convertToDTO) //Convert each Status entity to StatusDTO
            .collect(Collectors.toList());
    }

    @Override
    public StatusDTO getStatusById(Long id){
        Optional<Status> status = statusRepository.findById(id);
        Status foundStatus = status.orElseThrow(() -> new RuntimeException("Status not found with id: " + id));
        return convertToDTO(foundStatus);
    }

}
