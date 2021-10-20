package com.example.swagger.repository;

import com.example.swagger.aggregators.OrderAggregator;
import com.example.swagger.entity.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderRepositoryTest {


    private final StoreDao storeDao;


    @Autowired
    public OrderRepositoryTest(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    @BeforeAll
    public void init(){
        Pet pet = Pet.builder().
                category(Category.builder().name("cat").build()).name("LUSI")
        .tag(List.of(Tag.builder().name("#BEST").build(),Tag.builder().name("#WAY").build()))
                .petStatus(PetStatus.AVAILABLE)
                .build();
        Pet pet2 = Pet.builder().
                category(Category.builder().name("dog").build()).name("Milka")
                .tag(List.of(Tag.builder().name("#BESTDOG").build(),Tag.builder().name("#WAY").build()))
                .petStatus(PetStatus.SOLD)
                .build();
        Order order = Order.builder().pet(pet)
                .shipDate(LocalDateTime.now())
                .orderStatus(OrderStatus.APPROVED)
                .complete(false)
                .build();
        Order order2 = Order.builder().pet(pet2)
                .shipDate(LocalDateTime.now())
                .orderStatus(OrderStatus.DELIVERED)
                .complete(true)
                .build();
        storeDao.save(order);
        storeDao.save(order2);
    }

    @ParameterizedTest
    @CsvSource({"2, DELIVERED",
    "1, APPROVED"
    })
    @DisplayName("getByStatus")
    public void getByStatus(@AggregateWith(OrderAggregator.class) Order order){
        Order order1 = storeDao.getOrdersByOrderStatus(order.getOrderStatus()).get(0);
        assertEquals(order, order1);
    }



    @ParameterizedTest
    @CsvSource({"2, DELIVERED",
            "1, APPROVED"
    })
    @DisplayName("getById")
    public void getById(@AggregateWith(OrderAggregator.class) Order order){
        Optional<Order> order1 = storeDao.findById(order.getId());
        assertEquals(order, order1.get());
    }

    @ParameterizedTest
    @CsvSource({"2, DELIVERED"})
    @DisplayName("delete")
    public void delete(@AggregateWith(OrderAggregator.class) Order order){
        storeDao.delete(order);
        assertEquals(1, storeDao.findAll().size());
    }





}
