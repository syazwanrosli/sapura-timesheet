package com.sapura.timesheet.timesheet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapura.timesheet.timesheet_management.model.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

}
