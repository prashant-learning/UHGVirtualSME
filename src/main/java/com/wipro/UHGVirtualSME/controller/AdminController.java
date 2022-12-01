package com.wipro.UHGVirtualSME.controller;


import com.wipro.UHGVirtualSME.model.LoginRequest;
import com.wipro.UHGVirtualSME.model.User;
import com.wipro.UHGVirtualSME.model.UserLoginResponse;
import com.wipro.UHGVirtualSME.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/UHGVirtualSME")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authObject = null;
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authObject);
        User user = userRepository.findByUsername(loginRequest.getUserName()).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email:" + loginRequest.getUserName()));;
        return ResponseEntity.ok(user.toLoginResponse());
    }

}
