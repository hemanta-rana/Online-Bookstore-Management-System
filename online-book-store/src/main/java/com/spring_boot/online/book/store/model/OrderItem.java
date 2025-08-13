package com.spring_boot.online.book.store.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Integer quantity;

    private double price ;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // many items can be linked to one book
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


}
