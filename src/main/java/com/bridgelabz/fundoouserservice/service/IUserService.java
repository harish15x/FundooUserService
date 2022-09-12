package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.util.ResponseClass;

import java.util.List;

public interface IUserService {

    ResponseClass login(String emailId, String password);

    ResponseClass addUser(UserDTO userDTO);

    ResponseClass updateUser(String token, UserDTO userDTO, long userId);

    List<UserModel> getUserData(String token);

    ResponseClass deleteUser(long userId, String token);

    ResponseClass changePassword(String token, String password);

    ResponseClass resetPassword(String emailId);

    Boolean validate(String token);

    ResponseClass deletUserTemp(long userId, String token);

    ResponseClass deletePermanently(long userId, String token);

    ResponseClass restoreUser(long userId, String token);
}
