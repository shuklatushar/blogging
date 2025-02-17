package com.blogging.blogging.controller;


import com.blogging.blogging.payloads.ApiResponse;
import com.blogging.blogging.payloads.AuthRequest;
import com.blogging.blogging.payloads.AuthResponse;
import com.blogging.blogging.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtHelper jwtHelper;

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtHelper.generateToken(authRequest.getUsername());

                return ResponseEntity.ok(new AuthResponse(jwt));
            }

        }



