package com.cengizhanyavuz.flightsearchapi.controller;

import com.cengizhanyavuz.flightsearchapi.business.dto.user.AuthenticationRequest;
import com.cengizhanyavuz.flightsearchapi.business.dto.user.AuthenticationResponse;
import com.cengizhanyavuz.flightsearchapi.business.dto.user.RegisterRequest;
import com.cengizhanyavuz.flightsearchapi.business.service.impl.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
@Api(value = "Authentication API", tags = {"Authentication"})
public class AuthenticationApi {

    private final AuthenticationService service;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationApi.class);

    @PostMapping("/register")
    @ApiOperation(value = "Register a new user", response = AuthenticationResponse.class)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            logger.error("register method threw an exception", e);
            throw e;
        }
    }

    @PostMapping("/authenticate")
    @ApiOperation(value = "Authenticate user", response = AuthenticationResponse.class)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            logger.error("authenticate method threw an exception", e);
            throw e;
        }
    }

    @PostMapping("/refresh-token")
    @ApiOperation(value = "Refresh authentication token", notes = "This endpoint refreshes the JWT token.")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}
