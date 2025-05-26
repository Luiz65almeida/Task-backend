package com.example.task_api_backend.service;

import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.task_api_backend.dto.AuthRequestDto;
import com.example.task_api_backend.dto.AuthResponseDto;
import com.example.task_api_backend.dto.UserCreateDto;
import com.example.task_api_backend.model.Role;
import com.example.task_api_backend.model.User;
import com.example.task_api_backend.repository.RoleRepository;
import com.example.task_api_backend.repository.UserRepository;
import com.example.task_api_backend.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthSerivce {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    public void register(UserCreateDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Perfil padrão não encontrado."));

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Collections.singleton(roleUser));

        userRepository.save(user);
    }

    public AuthResponseDto authenticate(AuthRequestDto request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = (User) auth.getPrincipal();
        String token = jwtTokenProvider.generateToken(user);
        return new AuthResponseDto(token);
    }
}
