package com.spring_boot.online.book.store.mapper;

import com.spring_boot.online.book.store.dto.OrderDTO;
import com.spring_boot.online.book.store.dto.OrderItemDTO;
import com.spring_boot.online.book.store.model.Order;
import com.spring_boot.online.book.store.model.OrderItem;
import com.spring_boot.online.book.store.model.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private  final OrderItemMapper orderItemMapper;

    // map  orderEntity to OrderDTo
    public OrderDTO mapToOrderDTO (Order order){

        if (order == null){
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        if (order.getOrderItems()!= null){
            List<OrderItemDTO> orderItemDTOS = order.getOrderItems()
                    .stream()
                    .map(orderItemMapper::mapToOrderItemDTO).toList();
            orderDTO.setOrderItems(orderItemDTOS);
        }else {
            orderDTO.setOrderItems(Collections.emptyList());
        }


        return orderDTO;
    }

    // map OrderDTO to order Entity
    // Accept User, OrderItems (mapped in Service)
    public Order mapToOrderEntity(OrderDTO orderDTO, User user, List<OrderItem> orderItems) {
        if (orderDTO == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setStatus(orderDTO.getStatus());

        // Set orderDate only for new orders
        if (orderDTO.getId() == null) {
            order.setOrderDate(LocalDateTime.now());
        }

        order.setUser(user);
        order.setOrderItems(orderItems != null ? orderItems : Collections.emptyList());
        order.setTotalPrice(orderDTO.getTotalPrice());

        return order;
    }
}
