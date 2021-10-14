package com.example.swagger.repository;

import com.example.swagger.entity.Order;
import com.example.swagger.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreDao extends JpaRepository<Order,Long> {
    List<Order> getOrdersByOrderStatus(OrderStatus orderStatus);
}
