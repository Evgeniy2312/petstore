package com.example.swagger.service;

import com.example.swagger.aggregators.OrderAggregator;
import com.example.swagger.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StoreServiceTest {

    private final StoreService service;

    @Autowired
    public StoreServiceTest(StoreService service) {
        this.service = service;
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
        service.addOrder(order);
        service.addOrder(order2);
    }


    @ParameterizedTest
    @CsvSource({"2, DELIVERED",
            "1, APPROVED"
    })
    @DisplayName("addOrder")
    void addOrder(@AggregateWith(OrderAggregator.class) Order order) {
        service.addOrder(order);
        assertEquals(2, service.getAll().size());
    }

    @ParameterizedTest
    @CsvSource({"2, DELIVERED"})
    @DisplayName("getByStatus")
    void getOrderByStatus(@AggregateWith(OrderAggregator.class) Order order) {
        Order order1 = service.getOrderByStatus(order.getOrderStatus().toString()).get(0);
        assertEquals(order, order1);
    }

    @ParameterizedTest
    @CsvSource({"2, DELIVERED"})
    @DisplayName("getById")
    void getById(@AggregateWith(OrderAggregator.class) Order order) {
        Optional<Order> order1 = service.getById(order.getId());
        assertEquals(order, order1.get());
    }

    @ParameterizedTest
    @CsvSource({"1, APPROVED"})
    @DisplayName("delete")
    void deleteOrder(@AggregateWith(OrderAggregator.class) Order order) {
        service.deleteOrder(order.getId());
        assertEquals(1, service.getAll().size());
    }

    @Test
    void getAll() {
        assertEquals(2, service.getAll().size());
    }
}