package com.spring_boot.online.book.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdDate;

    // One cart belongs to one user
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many books can be in many carts
    @ManyToMany
    @JoinTable(name = "cart_books",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();
}

