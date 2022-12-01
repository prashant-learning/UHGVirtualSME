package com.wipro.UHGVirtualSME.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserLoginResponse {

    private String username;
    private boolean enabled;
    private boolean isFirstLogin;
    private String fullname;
    private String department;
    private String region;
    private String emailID;
    private String employeeid;
    private String supervisor;
    private String manager;
    private String role;
}
