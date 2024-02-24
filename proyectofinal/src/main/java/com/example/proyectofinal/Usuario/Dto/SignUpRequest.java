package com.example.proyectofinal.Usuario.Dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private String description;
    private String img;
    private Boolean isAdmin;
    private Boolean isInvited;

}
