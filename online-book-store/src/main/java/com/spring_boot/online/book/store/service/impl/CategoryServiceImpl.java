package com.spring_boot.online.book.store.service.impl;

import com.spring_boot.online.book.store.dto.CategoryDTO;
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
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException("category id"+id +" not found "));
        category.setName(categoryDTO.getName());
        if (categoryDTO.getBooks() != null && !categoryDTO.getBooks().isEmpty()){
            List<Book> bookDTOS = categoryDTO.getBooks()
                    .stream()
                    .map(bookDTO -> bookMapper.mapToBookEntity(bookDTO, category)).toList();
            category.setBooks(bookDTOS);
        }else {
            category.setBooks(Collections.emptyList());
        }



        return categoryMapper.mapToCategoryDTO(categoryRepository.save(category));

    }

    @Override
    public void deleteCategory(Long id) {
       if (!categoryRepository.findById(id).isPresent()){
           throw  new CategoryNotFoundException("category id "+id+" not found ");
       }
       categoryRepository.deleteById(id);
    }


}
