package com.sapura.timesheet.timesheet_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapura.timesheet.timesheet_management.dto.TimesheetDTO;
import com.sapura.timesheet.timesheet_management.services.TimesheetService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService){
        this.timesheetService = timesheetService;
    }

    //GET all timesheets
    @GetMapping
    public ResponseEntity<List<TimesheetDTO>> getAllTimesheets(){
        List<TimesheetDTO> timesheets = timesheetService.getAllTimesheet();
        return new ResponseEntity<>(timesheets, HttpStatus.OK);
    }

    //GET a single timesheet by ID
    @GetMapping("/{id}")
    public ResponseEntity<TimesheetDTO> getTimesheetById(@PathVariable Long id){
        TimesheetDTO timesheetdDto = timesheetService.getTimesheetById(id);
        return new ResponseEntity<>(timesheetdDto, HttpStatus.OK);
    }

    //POST: create a new timesheet
    @PostMapping
    public ResponseEntity<TimesheetDTO> createTimesheet(@RequestBody TimesheetDTO timesheetDTO){
        TimesheetDTO newTimesheetdDto = timesheetService.createTimesheet(timesheetDTO);
        return new ResponseEntity<>(newTimesheetdDto, HttpStatus.OK);
    }

    //PUT: update an existing timesheet
    @PutMapping("/{id}")
    public ResponseEntity<TimesheetDTO> updateTimesheet(@PathVariable Long id, @RequestBody TimesheetDTO timesheetDTO) {
        TimesheetDTO updatedTimesheetdDto = timesheetService.updateTimesheet(id, timesheetDTO);
        return new ResponseEntity<>(updatedTimesheetdDto, HttpStatus.OK);
    } 

    //DELETE: Delete timesheet by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTimesheet(@PathVariable Long id){
        boolean isDeleted = timesheetService.deleteTimesheet(id);
        if (isDeleted) {
            return ResponseEntity.ok("Timesheet with ID " + id + " deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Timesheet not found with ID " + id);
        }
    }

}
