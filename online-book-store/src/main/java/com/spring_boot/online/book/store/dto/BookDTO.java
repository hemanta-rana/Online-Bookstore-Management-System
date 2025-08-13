package com.spring_boot.online.book.store.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.java.Log;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {

    private Long id;

    @NotBlank(message = "Title is required ")
    private String title ;

    private String author ;

    private String description ;

    @NotBlank
    private Integer stock ;

    @NotBlank
    @Min(value = 1, message = "price must be least 1 ")
    private double price ;
    @NotNull(message = "category Id is required ")
    private Long categoryId;


}
