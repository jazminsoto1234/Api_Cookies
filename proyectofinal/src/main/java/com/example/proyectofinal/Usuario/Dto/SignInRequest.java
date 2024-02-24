package com.example.proyectofinal.Usuario.Dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
