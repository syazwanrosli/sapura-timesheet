package com.sapura.timesheet.timesheet_management.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sapura.timesheet.timesheet_management.dto.UserDTO;
import com.sapura.timesheet.timesheet_management.model.User;
import com.sapura.timesheet.timesheet_management.repository.UserRepository;
import com.sapura.timesheet.timesheet_management.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //Convert Entity to DTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName());
    }
    //Convert DTO to Entity
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        return user;
    }

    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(this::convertToDTO) //Convert each user entity to UserDTO
            .collect(Collectors.toList());
    }
    
    @Override 
    public UserDTO getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        User foundUser = user.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDTO(foundUser);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        //Convert DTO to entity
        User user = convertToEntity(userDTO);
        //Save the entity
        User savedUser = userRepository.save(user);
        //Convert back saved entity to DTO and return
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO){
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()){
            User user = existingUser.get();
            user.setName(updatedUserDTO.getName());
            User savedUser = userRepository.save(user);
            return convertToDTO(savedUser);
        } else {
            throw new RuntimeException("User not found with id: "+ id);
        }
    }

    public boolean deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
