package com.spring_boot.online.book.store.service.impl;

import com.spring_boot.online.book.store.dto.BookDTO;

import com.spring_boot.online.book.store.exception.BookNotFoundException;
import com.spring_boot.online.book.store.exception.CategoryNotFoundException;
import com.spring_boot.online.book.store.mapper.BookMapper;
import com.spring_boot.online.book.store.model.Book;
import com.spring_boot.online.book.store.model.Category;
import com.spring_boot.online.book.store.repository.BookRepository;
import com.spring_boot.online.book.store.repository.CategoryRepository;
import com.spring_boot.online.book.store.service.BookService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {

        Category category = categoryRepository.findById(bookDTO.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("category not found "));
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setStock(bookDTO.getStock());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setCategory(category);

        return bookMapper.mapToBookDto(bookRepository.save(book));

    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> books = bookRepository.findAll();
        return books
                .stream()
                .map(bookMapper::mapToBookDto).toList();
    }

    @Override
    public BookDTO getBookById(Long id) {
       Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book id "+id+" not found "));
       return bookMapper.mapToBookDto(book);
    }


    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("book id "+id+" not found "));
        Category category = categoryRepository.findById(bookDTO.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("category id "+id+"not found"));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setStock(bookDTO.getStock());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setCategory(category);


        return bookMapper.mapToBookDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (bookRepository.findById(id).isEmpty()){
            throw  new BookNotFoundException("book id "+id+" not found");
        }
        bookRepository.deleteById(id);
    }
    @Override
    public Page<BookDTO> searchBooks(
            String title, String author, Long categoryId,
            Double minPrice, Double maxPrice,
            int page, int size, String sortBy, String sortDir
    ) {
        Sort.Direction direction = Sort.Direction.fromOptionalString(sortDir).orElse(Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Specification<Book> spec = null; // start with null

        if (title != null && !title.isBlank()) {
            Specification<Book> titleSpec = (root, query, cb) ->
                    cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
            spec = (spec == null) ? titleSpec : spec.and(titleSpec);
        }

        if (author != null && !author.isBlank()) {
            Specification<Book> authorSpec = (root, query, cb) ->
                    cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
            spec = (spec == null) ? authorSpec : spec.and(authorSpec);
        }

        if (categoryId != null) {
            Specification<Book> categorySpec = (root, query, cb) ->
                    cb.equal(root.get("category").get("id"), categoryId);
            spec = (spec == null) ? categorySpec : spec.and(categorySpec);
        }

        if (minPrice != null) {
            Specification<Book> minPriceSpec = (root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            spec = (spec == null) ? minPriceSpec : spec.and(minPriceSpec);
        }

        if (maxPrice != null) {
            Specification<Book> maxPriceSpec = (root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            spec = (spec == null) ? maxPriceSpec : spec.and(maxPriceSpec);
        }

        return bookRepository.findAll(spec, pageable)
                .map(bookMapper::mapToBookDto);
    }




    @Override
    public Page<BookDTO> getBooksPage(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return bookRepository.findAll(pageable)
                .map(bookMapper::mapToBookDto);
    }

    @Override
    public Page<BookDTO> getBooksByCategory(Long categoryId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Book> bookPage = bookRepository.findByCategoryId(categoryId, pageable);

        return bookPage.map(bookMapper::mapToBookDto);
    }


}
