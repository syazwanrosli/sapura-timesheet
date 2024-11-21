package com.sapura.timesheet.timesheet_management.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sapura.timesheet.timesheet_management.dto.TimesheetDTO;
import com.sapura.timesheet.timesheet_management.model.Status;
import com.sapura.timesheet.timesheet_management.model.Timesheet;
import com.sapura.timesheet.timesheet_management.model.User;
import com.sapura.timesheet.timesheet_management.repository.StatusRepository;
import com.sapura.timesheet.timesheet_management.repository.TimesheetRepository;
import com.sapura.timesheet.timesheet_management.repository.UserRepository;
import com.sapura.timesheet.timesheet_management.services.TimesheetService;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;


    public TimesheetServiceImpl(TimesheetRepository timesheetRepository, UserRepository userRepository,
            StatusRepository statusRepository) {
        this.timesheetRepository = timesheetRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    //Conversion from Entity to DTO
    private TimesheetDTO convertToDTO(Timesheet timesheet) {
        return new TimesheetDTO(
            timesheet.getId(),
            timesheet.getProjectName(),
            timesheet.getTaskName(),
            timesheet.getAssignedTo().getName(),  // Get User ID from assigned user
            timesheet.getStatus().getStatusName(),  // Get Status ID
            timesheet.getStartDate(),
            timesheet.getEndDate()     
        );
    }
    // Convert from DTO to Entity
    private Timesheet convertToEntity(TimesheetDTO timesheetDTO, User assignedTo, Status status) {
        Timesheet timesheet = new Timesheet();
        timesheet.setProjectName(timesheetDTO.getProjectName());
        timesheet.setTaskName(timesheetDTO.getTaskName());
        timesheet.setStartDate(timesheetDTO.getStartDate());
        timesheet.setEndDate(timesheetDTO.getEndDate());
        timesheet.setAssignedTo(assignedTo);
        timesheet.setStatus(status);
        return timesheet;
        
    }

    @Override
    public List<TimesheetDTO> getAllTimesheet(){
        List<Timesheet> timesheets = timesheetRepository.findAll();
        return timesheets.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    @Override
    public TimesheetDTO getTimesheetById(Long id){
        Optional<Timesheet> timesheet = timesheetRepository.findById(id);
        Timesheet foundTimesheet = timesheet.orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + id));
        return convertToDTO(foundTimesheet);
    }

    @Override
    public TimesheetDTO createTimesheet(TimesheetDTO timesheetDTO){
        // fetch the related entities (user and status) by ID'S
        User assignedTo = userRepository.findByName(timesheetDTO.getAssignedUserName())
            .orElseThrow(() -> new RuntimeException("User not found with name: " + timesheetDTO.getAssignedUserName()));
        Status status = statusRepository.findByStatusName(timesheetDTO.getStatusName())
            .orElseThrow(() -> new RuntimeException("Status not found with name: " + timesheetDTO.getStatusName()));
        
        //convert DTO to entity
        Timesheet timesheet = convertToEntity(timesheetDTO, assignedTo, status);
        //save timesheet entity
        Timesheet savedTimesheet = timesheetRepository.save(timesheet);
        //convert entity back to DTO and return
        return convertToDTO(savedTimesheet);
    }

    public TimesheetDTO updateTimesheet(Long id, TimesheetDTO updatedTimesheetDTO){
        Optional<Timesheet> existingTimesheet = timesheetRepository.findById(id);
        if (existingTimesheet.isPresent()){
            Timesheet timesheet = existingTimesheet.get();

            // fetch the related entities (user and status) by ID'S
            User assignedTo = userRepository.findByName(updatedTimesheetDTO.getAssignedUserName())
                .orElseThrow(() -> new RuntimeException("User not found with name: " + updatedTimesheetDTO.getAssignedUserName()));
            Status status = statusRepository.findByStatusName(updatedTimesheetDTO.getStatusName())
                .orElseThrow(() -> new RuntimeException("Status not found with name: " + updatedTimesheetDTO.getStatusName()));


            timesheet.setProjectName(updatedTimesheetDTO.getProjectName());
            timesheet.setTaskName(updatedTimesheetDTO.getTaskName());
            timesheet.setStartDate(updatedTimesheetDTO.getStartDate());
            timesheet.setEndDate(updatedTimesheetDTO.getEndDate());
            timesheet.setAssignedTo(assignedTo); //set the updated User entity
            timesheet.setStatus(status); //set the updated Status entity
            //Save and return updated timesheet
            Timesheet savedTimesheet = timesheetRepository.save(timesheet);
            return convertToDTO(savedTimesheet);
        } else {
            throw new RuntimeException("Timesheet not found with id: " + id);
        }
    }

    @Override
    public boolean deleteTimesheet(Long id){
        Optional<Timesheet> timesheet = timesheetRepository.findById(id);
        if (timesheet.isPresent()){
            timesheetRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Timesheet not found with ID "+ id);
        }
    }

}
