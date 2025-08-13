package com.spring_boot.online.book.store.repository;

import com.spring_boot.online.book.store.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
