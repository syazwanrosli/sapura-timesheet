package com.sapura.timesheet.timesheet_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapura.timesheet.timesheet_management.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

    Optional<Status> findByStatusName(String statusName);

}
