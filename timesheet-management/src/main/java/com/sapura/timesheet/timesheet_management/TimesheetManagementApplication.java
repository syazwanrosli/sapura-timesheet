package com.sapura.timesheet.timesheet_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class TimesheetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetManagementApplication.class, args);
	}

}
