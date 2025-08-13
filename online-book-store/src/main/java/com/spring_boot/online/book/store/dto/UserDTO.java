package com.spring_boot.online.book.store.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id ;
    @NotBlank(message = "username is required ")
    private  String username ;

    @NotBlank(message = "password is required ")
    private String password;

    @Email(message = "Email should be valid ")
    private String email;

    private Set<String> roles = new HashSet<>(); // for storing name of role


}
