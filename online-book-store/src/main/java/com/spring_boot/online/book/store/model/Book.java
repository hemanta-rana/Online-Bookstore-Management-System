package com.spring_boot.online.book.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title ;
    private String author ;

    @Column(nullable = false)
    private double price;
    @NotNull
    private  Integer stock ;

    @Column(length = 1000)
    private String description ;

    // relation with category -->  many books belong to on category
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

//    //relation with orderitem
//    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems;


}
