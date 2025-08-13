package com.spring_boot.online.book.store.repository;

import com.spring_boot.online.book.store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
