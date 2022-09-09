package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.exception.UserNotFoundException;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.repository.UserRepository;
import com.bridgelabz.fundoouserservice.util.ResponseClass;
import com.bridgelabz.fundoouserservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Override
    public ResponseClass login(String emailId, String password) {
        Optional<UserModel> isEmailPresent = userRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)){
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new ResponseClass(200, "sucessfull", isEmailPresent.get());
            }
            throw new UserNotFoundException(400, "password is incorrect");
        }
        throw new UserNotFoundException(400,"no user is present by this email");
    }

    @Override
    public ResponseClass addUser(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        userModel.setCreatedDate(LocalDateTime.now());
        userRepository.save(userModel);
        String body = "User has been added sucessfully " + userModel.getId();
        String subject = "User registration completed";
        mailService.send(userModel.getEmailId(), body, subject);
        return new ResponseClass(200, "Successful", userModel);
    }

    @Override
    public ResponseClass updateUser(String token, UserDTO userDTO, long id) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            Optional<UserModel> isUserAvialable = userRepository.findById(id);
            if (isUserAvialable.isPresent()) {
                isUserAvialable.get().setName(userDTO.getName());
                isUserAvialable.get().setEmailId(userDTO.getEmailId());
                isUserAvialable.get().setPassword(userDTO.getPassword());
                isUserAvialable.get().setMobileNumber(userDTO.getMobileNumber());
                isUserAvialable.get().setProfilePic(userDTO.getProfilePic());
                isUserAvialable.get().setIsActive(userDTO.getIsActive());
                isUserAvialable.get().setIsDeleted(userDTO.getIsDeleted());
                isUserAvialable.get().setCreatedDate(userDTO.getCreatedDate());
                isUserAvialable.get().setUpdatedDate(userDTO.getUpdatedDate());
                return new ResponseClass(200, "Sucessfull", isUserAvialable.get());
            }
            throw new UserNotFoundException(400, "User not Found");
        }
        throw new UserNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<UserModel> getUserData(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            List<UserModel> isUserAvailable = userRepository.findAll();
            if (isUserAvailable.size() > 0) {
                return isUserAvailable;
            }
            throw new UserNotFoundException(400, "user not found");
        }
        throw new UserNotFoundException(400, "Tokken is wrong");
    }

    @Override
    public ResponseClass deleteUser(long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            Optional<UserModel> isUserAvailable = userRepository.findById(id);
            if (isUserAvailable.isPresent()) {
                userRepository.save(isUserAvailable.get());
                return new ResponseClass(200, "Sucessfull", isUserAvailable.get());
            }
            throw new UserNotFoundException(400, "user not found");
        }
        throw new UserNotFoundException(400, "token is wrong");
    }

    @Override
    public ResponseClass deleteTemp(String token, long id) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isUserPresent = userRepository.findById(userId);
        if (isUserPresent.isPresent()){
        }
        return null;
    }


}
