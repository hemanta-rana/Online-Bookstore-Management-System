package com.spring_boot.online.book.store.dto;

import com.spring_boot.online.book.store.model.OrderItem;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    private double totalPrice;

    private Long userId;

    private LocalDateTime orderDate;

    private String status ;

    private List<OrderItemDTO> orderItems ;
}
