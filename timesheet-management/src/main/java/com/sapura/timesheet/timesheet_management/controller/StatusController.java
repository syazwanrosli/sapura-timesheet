package com.sapura.timesheet.timesheet_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapura.timesheet.timesheet_management.dto.StatusDTO;
import com.sapura.timesheet.timesheet_management.services.StatusService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    //GET all status
    @GetMapping
    public ResponseEntity<List<StatusDTO>> getAllStatus() {
        List<StatusDTO> status = statusService.getAllStatus();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    //GET single status by ID
    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> getStatusById(@PathVariable Long id){
        StatusDTO statusdDto = statusService.getStatusById(id);
        return new ResponseEntity<>(statusdDto, HttpStatus.OK);

    }
    

}
