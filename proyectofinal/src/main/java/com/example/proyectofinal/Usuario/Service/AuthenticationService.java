package com.example.proyectofinal.Usuario.Service;

import com.example.proyectofinal.Usuario.Domain.Role;
import com.example.proyectofinal.Usuario.Domain.UserEntity;
import com.example.proyectofinal.Usuario.Domain.UserRepository;
import com.example.proyectofinal.Usuario.Dto.JwtAuthenticationResponse;
import com.example.proyectofinal.Usuario.Dto.SignInRequest;
import com.example.proyectofinal.Usuario.Dto.SignUpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    //Crear un dto de jwtauthentication response para que almacene el nuevo token creado
    public JwtAuthenticationResponse signup(SignUpRequest signUpRequest){
        UserEntity user = new UserEntity();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setDescription(signUpRequest.getDescription());
        user.setImgprofile(signUpRequest.getImg());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRol(Role.USER);
        if(signUpRequest.getIsAdmin()){
            user.setRol(Role.ADMIN);
        }else if(signUpRequest.getIsInvited()){
            user.setRol(Role.INVITED);
        }
        userRepository.save(user);

        var jwt = jwtService.generateAccessToken(user); //La variable var es ---

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);

        return response;

    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest) throws IllegalArgumentException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        System.out.println(signInRequest.getEmail());
        var user = userRepository.findByEmail(signInRequest.getEmail());
        var jwt = jwtService.generateAccessToken(user.get()); // Investigar por que sucedio esto
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        return response;

    }


}
