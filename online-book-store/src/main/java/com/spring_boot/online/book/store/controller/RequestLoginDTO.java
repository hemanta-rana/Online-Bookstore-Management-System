package com.spring_boot.online.book.store.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDTO {
    @NotBlank(message = "username  is required ")
    String username ;
    @NotBlank(message = "password is required ")
    String password ;


}
