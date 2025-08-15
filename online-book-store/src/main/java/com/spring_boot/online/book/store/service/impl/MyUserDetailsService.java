package com.spring_boot.online.book.store.service.impl;

import com.spring_boot.online.book.store.dto.UserDTO;
import com.spring_boot.online.book.store.dto.UserPrincipal;
import com.spring_boot.online.book.store.mapper.UserMapper;
import com.spring_boot.online.book.store.model.Role;
import com.spring_boot.online.book.store.model.User;
import com.spring_boot.online.book.store.repository.RoleRepository;
import com.spring_boot.online.book.store.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public MyUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username)
               .orElseThrow(()-> new UsernameNotFoundException("username not found "));
       return new UserPrincipal(user);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new IllegalArgumentException("Username already exists");
        }
        Set<Role> roles = userDTO.getRoles()
                .stream()
                .map(roleName ->roleRepository.findByName(roleName)
                        .orElseThrow(()->new IllegalArgumentException("ROle not found ")))
                .collect(Collectors.toSet());

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder(12).encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRoles(roles);
        userRepository.save(user);

        return userMapper.mapToUserDTO(user);
    }
}
