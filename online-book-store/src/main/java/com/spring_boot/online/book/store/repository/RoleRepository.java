package com.spring_boot.online.book.store.repository;

import com.spring_boot.online.book.store.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
