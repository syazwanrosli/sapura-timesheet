package com.sapura.timesheet.timesheet_management.services;

import java.util.List;

import com.sapura.timesheet.timesheet_management.dto.TimesheetDTO;

public interface TimesheetService {
    List<TimesheetDTO>getAllTimesheet();
    TimesheetDTO getTimesheetById(Long id);
    TimesheetDTO createTimesheet(TimesheetDTO timesheet);
    TimesheetDTO updateTimesheet(Long id, TimesheetDTO updatedTimesheet);
    boolean deleteTimesheet(Long id);

}
