package com.spring_boot.online.book.store.mapper;

import com.spring_boot.online.book.store.dto.BookDTO;
import com.spring_boot.online.book.store.dto.CategoryDTO;
import com.spring_boot.online.book.store.model.Book;
import com.spring_boot.online.book.store.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
        private final BookMapper bookMapper;

    // converting the categoryDto to category
    public Category mapToCategoryEntity (CategoryDTO categoryDTO){
        if (categoryDTO == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        if (categoryDTO.getBooks() != null){
            List<Book> books = categoryDTO.getBooks()
                    .stream()
                    .map( bookDTO -> bookMapper.mapToBookEntity(bookDTO, category))
                    .toList();
            category.setBooks(books);
        }else {
            category.setBooks(Collections.emptyList()); // ✅ Avoid null
        }
        return category;



    }

    // convert the category Entity to category DTO
    public  CategoryDTO mapToCategoryDTO(Category category){
        if (category == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        if (category.getBooks() != null){
            List<BookDTO> books = category.getBooks()
                    .stream()
                    .map(bookMapper::mapToBookDto)
                    .toList();
            categoryDTO.setBooks(books);
        }else {
            categoryDTO.setBooks(Collections.emptyList());
        }
        return categoryDTO;
    }
}
