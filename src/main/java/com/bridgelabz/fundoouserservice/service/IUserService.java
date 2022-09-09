package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.util.ResponseClass;

import java.util.List;

public interface IUserService {

    ResponseClass login(String emailId, String password);

    ResponseClass addUser(UserDTO userDTO);

    ResponseClass updateUser(String token, UserDTO userDTO, long id);

    List<UserModel> getUserData(String token);

    ResponseClass deleteUser(long id, String token);

    ResponseClass deleteTemp(String token, long id);
}
