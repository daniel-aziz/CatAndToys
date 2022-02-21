package com.jb.SpringProject.beans.Users;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDetails {
    private String email;
    private String password;
    private String userType;
}
