package com.wipro.UHGVirtualSME.model;

import lombok.*;

//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

/**
 * {"username":"agent1","enabled":true,"isFirstLogin":true,"fullname":"Agent1","department":"BNE",
 * "region":"Airoli","emailID":"a@b.com","employeeid":"1","supervisor":"na","manager":"na","role":"ROLE_USER"}
 */

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private boolean enabled;
    private boolean isFirstLogin;
    private String fullname;

    @NotBlank
    private String department;

    @NotBlank
    private String region;
    private String emailID;
    private String employeeid;
    private String supervisor;
    private String manager;

    @NotBlank
    private String role;

    public UserLoginResponse toLoginResponse(){

      return   UserLoginResponse.builder()
                .emailID(emailID)
                .isFirstLogin(isFirstLogin)
                .department(department)
                .enabled(enabled)
                .employeeid(employeeid)
                .fullname(fullname)
                .manager(manager)
                .region(region)
                .role(role)
                .supervisor(supervisor)
                .username(username)
                .build();

    }

}
