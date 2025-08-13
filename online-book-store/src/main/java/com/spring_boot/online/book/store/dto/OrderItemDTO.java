package com.spring_boot.online.book.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id ;

    private Integer quantity;

    private double price ;


    private Long orderID;

    private Long bookID;


}
