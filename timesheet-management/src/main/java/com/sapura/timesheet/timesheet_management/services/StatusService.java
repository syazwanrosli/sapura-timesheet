package com.sapura.timesheet.timesheet_management.services;

import java.util.List;

import com.sapura.timesheet.timesheet_management.dto.StatusDTO;

public interface StatusService {
    List<StatusDTO> getAllStatus();
    StatusDTO getStatusById(Long id);

}
