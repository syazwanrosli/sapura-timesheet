package com.sapura.timesheet.timesheet_management.dto;

public class StatusDTO {

    private Long id;
    private String statusName;

    //Constructors
    public StatusDTO(Long id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }    
    

}
