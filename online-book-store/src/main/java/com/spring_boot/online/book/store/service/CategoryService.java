package com.spring_boot.online.book.store.service;

import com.spring_boot.online.book.store.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);


    List<CategoryDTO> getAllCategory();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

    void deleteCategory(Long id);
}
