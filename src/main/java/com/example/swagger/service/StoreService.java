package com.example.swagger.service;


import com.example.swagger.entity.Order;
import com.example.swagger.entity.OrderStatus;
import com.example.swagger.repository.StoreDao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class StoreService {

    private final StoreDao storeDao;

    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public boolean addOrder(Order order){
        if(!storeDao.existsById(order.getId())){
            order.setShipDate(LocalDateTime.now());
            storeDao.save(order);
            return true;
        }else return false;
    }

    public List<Order> getOrderByStatus(String status){
        return storeDao.getOrdersByOrderStatus(OrderStatus.valueOf(status.toUpperCase(Locale.ENGLISH)));
    }

    public Optional<Order> getById(long id){
        return storeDao.findById(id);
    }

    public boolean deleteOrder(long id){
        if(storeDao.existsById(id)){
            storeDao.delete(storeDao.getById(id));
            return true;
        }else return false;
    }

    public List<Order> getAll(){
        return storeDao.findAll();
    }
}
