package com.example.swagger.dao;

import com.example.swagger.entity.Order;
import java.util.List;
import java.util.Optional;

public interface StoreDao {

    List<Order> getOrderByStatus(String status);

    void addOrder(Order order);

    Order getById(long id);

    void deleteOrder(long id);

    boolean isExistOrder(Order order);

    List<Order> getAll();

}
