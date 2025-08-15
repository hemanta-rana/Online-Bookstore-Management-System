package com.spring_boot.online.book.store.service.impl;

import com.spring_boot.online.book.store.dto.CategoryDTO;
import com.spring_boot.online.book.store.exception.CategoryAlreadyExistException;
import com.spring_boot.online.book.store.exception.CategoryNotFoundException;
import com.spring_boot.online.book.store.mapper.BookMapper;
import com.spring_boot.online.book.store.mapper.CategoryMapper;
import com.spring_boot.online.book.store.model.Book;
import com.spring_boot.online.book.store.model.Category;
import com.spring_boot.online.book.store.repository.CategoryRepository;
import com.spring_boot.online.book.store.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = categoryMapper.mapToCategoryEntity(categoryDTO);
        if (categoryRepository.findByNameIgnoreCase(categoryDTO.getName()).isPresent()){
            throw new CategoryAlreadyExistException("category with name "+categoryDTO.getName()+" already exist");
        }
        return categoryMapper.mapToCategoryDTO(categoryRepository.save(category));

    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        return categories
                .stream()
                .map(categoryMapper::mapToCategoryDTO).toList();

    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category Id "+id+" not found "));

        return categoryMapper.mapToCategoryDTO(category);


    }
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id) {
        // Find existing category or throw exception if not found
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category ID " + id + " not found"));

        // Check if the new name conflicts with another existing category
        categoryRepository.findByNameIgnoreCase(categoryDTO.getName())
                .filter(category -> !category.getId().equals(id)) // exclude the current category
                .ifPresent(category -> {
                    throw new CategoryAlreadyExistException("Category with name '" + categoryDTO.getName() + "' already exists");
                });

        // Update category details
        existingCategory.setName(categoryDTO.getName());

        // Clear and update books if provided
        existingCategory.getBooks().clear();
        if (categoryDTO.getBooks() != null && !categoryDTO.getBooks().isEmpty()) {
            List<Book> books = categoryDTO.getBooks()
                    .stream()
                    .map(bookDTO -> bookMapper.mapToBookEntity(bookDTO, existingCategory))
                    .toList();
            existingCategory.setBooks(books);
        }

        // Save and return
        return categoryMapper.mapToCategoryDTO(categoryRepository.save(existingCategory));
    }


    @Override
    public void deleteCategory(Long id) {
       if (!categoryRepository.findById(id).isPresent()){
           throw  new CategoryNotFoundException("category id "+id+" not found ");
       }
       categoryRepository.deleteById(id);
    }


}
