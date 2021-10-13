package com.example.swagger.service;

import com.example.swagger.dao.StoreDao;
import com.example.swagger.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StoreService {

    private final StoreDao storeDao;

    public StoreService(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public boolean addOrder(Order order){
        if(!storeDao.isExistOrder(order)){
            storeDao.addOrder(order);
            return true;
        }else return false;
    }

    public List<Order> getOrderByStatus(String status){
        return storeDao.getOrderByStatus(status);
    }

    public Optional<Order> getById(long id){
        return Optional.of(storeDao.getById(id));
    }

    public boolean deleteOrder(long id){
        if(storeDao.isExistOrder(storeDao.getById(id))){
            storeDao.deleteOrder(id);
            return true;
        }else return false;
    }

    public List<Order> getAll(){
        return storeDao.getAll();
    }
}
