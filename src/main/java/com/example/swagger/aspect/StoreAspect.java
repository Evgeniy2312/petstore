package com.example.swagger.aspect;


import com.example.swagger.entity.Order;
import com.example.swagger.entity.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StoreAspect {

    private final Logger logger = LoggerFactory.getLogger(StoreAspect.class);

    @Pointcut("execution(public * com.example.swagger.controller.StoreController.addOrder(..)) && args(order)")
    public void addOrder(Order order) {}

    @Pointcut("execution(public * com.example.swagger.controller.StoreController.getByStatus(..)) && args(status)")
    public void getByStatus(String status) {}

    @Pointcut("execution(public * com.example.swagger.controller.StoreController.getById(..)) && args(id)")
    public void getById(long id) {}

    @Pointcut("execution(public * com.example.swagger.controller.StoreController.delete(..)) && args(id)")
    public void delete(long id) {}

    @Pointcut("execution(public * com.example.swagger.controller.StoreController.getAll())")
    public void getAll() {}

    @After(value = "addOrder(order)", argNames = "order")
    public void add(Order order){
        logger.info("Order with id - {} was added", order.getId() );
    }


    @After(value = "getByStatus(status)", argNames = "status")
    public void status(String status){
        logger.info("Order with status - {} was received", status );
    }


    @After(value = "getById(id)", argNames = "id")
    public void getId(long id){
        logger.info("Order with id - {} was received", id );
    }

    @After(value = "delete(id)", argNames = "id")
    public void del(long id){
        logger.info("Order with id - {} was deleted", id );
    }

    @After(value = "getAll()")
    public void all(){
        logger.info("All orders were received");
    }

}
