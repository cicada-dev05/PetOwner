package com.example.PetOwner.utils;

import com.example.PetOwner.dtos.LoginRequestDTO;
import com.example.PetOwner.dtos.LoginResponseDTO;

import java.time.LocalDateTime;

public class Authorization {
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setToken("Token");
        loginResponseDTO.setUserId(loginRequestDTO.getUserId());
        loginResponseDTO.setCreatedTs(LocalDateTime.now());
        return loginResponseDTO;
    }
}
