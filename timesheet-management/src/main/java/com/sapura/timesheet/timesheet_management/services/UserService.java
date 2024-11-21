package com.sapura.timesheet.timesheet_management.services;

import java.util.List;

import com.sapura.timesheet.timesheet_management.dto.UserDTO;

public interface UserService {
    List<UserDTO>getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userdDto);
    UserDTO updateUser(Long id, UserDTO updatedUserdDto);
    boolean deleteUser(Long id);

}
