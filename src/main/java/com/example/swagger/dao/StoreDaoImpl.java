package com.example.swagger.dao;


import com.example.swagger.entity.Order;
import com.example.swagger.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class StoreDaoImpl implements StoreDao {

    private final List<Order> orders = new ArrayList<>();


    @Override
    public List<Order> getOrderByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getOrderStatus().equals(OrderStatus.valueOf(status.toUpperCase(Locale.ENGLISH))))
                .collect(Collectors.toList());
    }

    @Override
    public void addOrder(Order order) {
        orders.add(new Order(order.getId(), order.getPetId(), LocalDateTime.now(), order.getOrderStatus(), order.isComplete()));
    }

    @Override
    public Order getById(long id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().get();
    }

    @Override
    public void deleteOrder(long id) {
        orders.removeIf(o -> o.getId() == id);
    }

    @Override
    public boolean isExistOrder(Order order) {
        return orders.contains(order);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }
}
