package com.spring_boot.online.book.store.service;

import com.spring_boot.online.book.store.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> getAllBook();

    BookDTO getBookById(Long id);

    BookDTO updateBook(BookDTO bookDTO, Long id);

    void deleteBook(Long id);

    Page<BookDTO> searchBooks(String title, String author, Long categoryId, Double minPrice, Double maxPrice, int page, int size, String sortBy, String sortDir);


    Page<BookDTO> getBooksPage(int page, int size, String sortBy, String sortDir);

    Page<BookDTO> getBooksByCategory(Long categoryId, int page, int size, String sortBy, String sortDir);
}
