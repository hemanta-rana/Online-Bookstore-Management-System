package com.spring_boot.online.book.store.dto;


import com.spring_boot.online.book.store.model.Book;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id ;

    @NotBlank(message = "category name is required ")
    private String name ;

    private List<BookDTO> books = new ArrayList<>();
}
