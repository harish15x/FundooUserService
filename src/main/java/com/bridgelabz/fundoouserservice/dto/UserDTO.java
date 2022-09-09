package com.bridgelabz.fundoouserservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDTO {

    private String name;
    private String emailId;
    private String password;
    private int mobileNumber;
    private String profilePic;
    private String isActive;
    private String isDeleted;
    private Date DOB;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
