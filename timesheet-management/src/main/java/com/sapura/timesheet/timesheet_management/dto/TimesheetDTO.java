package com.sapura.timesheet.timesheet_management.dto;

import java.time.LocalDate;

public class TimesheetDTO {

    private Long id;
    private String projectName;
    private String taskName;
    private String assignedUserName;
    private String statusName;
    private LocalDate startDate;
    private LocalDate endDate;

    //Constructors
    public TimesheetDTO(Long id, String projectName, String taskName, String assignedUserName, String statusName,
            LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.projectName = projectName;
        this.taskName = taskName;
        this.assignedUserName = assignedUserName;
        this.statusName = statusName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getProjectName() {
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String getTaskName() {
        return taskName;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    public String getAssignedUserName() {
        return assignedUserName;
    }


    public void setAssignedUserName(String assignedUserName) {
        this.assignedUserName = assignedUserName;
    }


    public String getStatusName() {
        return statusName;
    }


    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    

    

}





    
