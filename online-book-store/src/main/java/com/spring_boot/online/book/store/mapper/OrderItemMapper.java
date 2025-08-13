package com.spring_boot.online.book.store.mapper;

import com.spring_boot.online.book.store.dto.OrderItemDTO;
import com.spring_boot.online.book.store.model.Book;
import com.spring_boot.online.book.store.model.Order;
import com.spring_boot.online.book.store.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    // map to OrderItem Entity to DTO
    public OrderItemDTO mapToOrderItemDTO (OrderItem orderItem){
        if (orderItem == null){
            return null;
        }
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setOrderID(orderItem.getOrder().getId());
        orderItemDTO.setBookID(orderItem.getBook().getId());
        return orderItemDTO;
    }

    // map to OrderItem DTO to Entity
    // Accept Book & Order from Service to avoid fetching inside Mapper
    public OrderItem mapToOrderItemEntity(OrderItemDTO orderItemDTO, Order order, Book book){
        if (orderItemDTO==null){
            return null;
        }
        OrderItem orderItem= new OrderItem();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setOrder(order);
        orderItem.setBook(book);
//        add book and order from the service
        return orderItem;
    }
}
