package com.example.proyectofinal.Usuario.Service;

import com.example.proyectofinal.Usuario.Domain.Role;
import com.example.proyectofinal.Usuario.Domain.UserEntity;
import com.example.proyectofinal.Usuario.Domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> opt1 = userRepository.findByUsername(username);
        Optional<UserEntity> opt = userRepository.findByEmail(username);


        org.springframework.security.core.userdetails.User springUser = null; //Documentar esto
        UserEntity user;

        if(opt.isPresent()){
            user = opt.get();
            Role rol = user.getRol();
            Set<GrantedAuthority> ga = new HashSet<>();
            ga.add(new SimpleGrantedAuthority((rol.name())));
            springUser = new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), ga
            );

        } else if(opt1.isPresent()) {
            user = opt1.get();
            //UserEntity user = opt1.get();
            //List<Role> roles = user.getRoles();
            //Set<GrantedAuthority> ga = new HashSet<>();
            //for(Role rol : roles){
            //ga.add(new SimpleGrantedAuthority(rol.name()));
            //}
            Role rol = user.getRol();
            Set<GrantedAuthority> ga = new HashSet<>();
            ga.add(new SimpleGrantedAuthority((rol.name())));
            springUser = new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), ga
            );

        }else{

            throw new UsernameNotFoundException("Usuario :"+ username + " no encontrado");
        }
        return springUser;

    }
}
