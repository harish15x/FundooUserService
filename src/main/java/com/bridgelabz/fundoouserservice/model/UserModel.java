package com.bridgelabz.fundoouserservice.model;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "usertable")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String emailId;
    private String password;
    private int mobileNumber;
    @Lob
    private byte[] profilePic;
    private Date DOB;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public UserModel (UserDTO userDTO){

        this.name = userDTO.getName();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
        this.mobileNumber = userDTO.getMobileNumber();
        this.DOB = userDTO.getDOB();

    }

    public UserModel() {

    }
}
