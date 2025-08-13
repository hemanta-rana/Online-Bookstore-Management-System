package com.spring_boot.online.book.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name ;

    // relation with book one category can have multiple books
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval =true)
    private  List<Book> books = new ArrayList<>();

}
