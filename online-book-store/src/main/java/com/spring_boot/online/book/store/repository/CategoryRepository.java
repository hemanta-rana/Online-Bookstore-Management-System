package com.spring_boot.online.book.store.repository;

import com.spring_boot.online.book.store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
