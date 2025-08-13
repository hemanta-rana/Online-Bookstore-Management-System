package com.spring_boot.online.book.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false, unique = true)
    private String name ;


    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

}
