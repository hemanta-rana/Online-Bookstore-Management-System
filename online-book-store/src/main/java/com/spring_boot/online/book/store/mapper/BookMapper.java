package com.spring_boot.online.book.store.mapper;


import com.spring_boot.online.book.store.dto.BookDTO;
import com.spring_boot.online.book.store.model.Book;
import com.spring_boot.online.book.store.model.Category;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    // convert the bookDTO into book entity
    public Book mapToBookEntity(BookDTO bookDTO, Category category){
        if (bookDTO == null){
            return null;
        }
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setStock(bookDTO.getStock());
        book.setCategory(category);
        book.setAuthor(bookDTO.getAuthor());

        return book;

    }

    // convert the book entity to book dto
    public BookDTO mapToBookDto (Book book){
        if (book == null){
            return null;
        }
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setStock(book.getStock());
        bookDTO.setCategoryId(book.getCategory().getId());

        return bookDTO;
    }



}
