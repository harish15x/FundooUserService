package com.bridgelabz.fundoouserservice.controller;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.service.IUserService;
import com.bridgelabz.fundoouserservice.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseClass> login(@RequestParam String emailId, @RequestParam String password){
        ResponseClass responseClass = userService.login(emailId, password);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }


    @PostMapping("/adduser")
    public ResponseEntity<ResponseClass> addUser(@Valid @PathVariable UserDTO userDTO){
        ResponseClass responseClass = userService.addUser(userDTO);
        return new ResponseEntity<>(responseClass , HttpStatus.OK);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<ResponseClass> upadateUser(@RequestHeader String token, @Valid @PathVariable UserDTO userDTO, @PathVariable long id){
        ResponseClass responseClass = userService.updateUser(token, userDTO, id);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity <List<?>> getUserdata(@RequestHeader String token){
        List<UserModel> responseClass = userService.getUserData(token);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<ResponseClass> deleteUser(@PathVariable long id, @RequestHeader String token){
        ResponseClass responseClass = userService.deleteUser(id, token);
        return new ResponseEntity<>(responseClass,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseClass> deleteTemp(@RequestHeader String token, @PathVariable long id){
        ResponseClass responseClass = userService.deleteTemp(token, id);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

}
