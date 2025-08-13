package com.spring_boot.online.book.store.repository;

import com.spring_boot.online.book.store.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
