package com.spring_boot.online.book.store.repository;

import com.spring_boot.online.book.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
