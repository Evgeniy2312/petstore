package com.example.swagger.service;

import com.example.swagger.aggregators.UserAggregator;
import com.example.swagger.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @BeforeAll
    public void init(){
        User user = User.builder().username("peter").firstName("Peter").lastName("Josh").userStatus(1)
                .email("peter@ewqe")
                .password("1234")
                .phone("213123233")
                .build();
        User user2 = User.builder().username("zhenya").firstName("Zhenya").lastName("Matveev").userStatus(0)
                .email("email@ewqe")
                .password("12312332")
                .phone("234111231")
                .build();
        userService.addUser(user);
        userService.addUser(user2);
    }


    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233",
            "2, zhenya, Zhenya, Matveev, 0, email@ewqe, 12312332, 234111231"
    })
    @DisplayName("addUser")
    void addUser(@AggregateWith(UserAggregator.class) User user){
        userService.addUser(user);
        assertEquals(2, userService.getAll().size());
    }


    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233",
            "3, Masha, Masenya, min, 1, email@masha, 112332, 211231"
    })
    @DisplayName("addUser")
    void addUsers(@AggregateWith(UserAggregator.class) User user) {
        userService.addUsers(List.of(user));
        assertEquals(3, userService.getAll().size());
    }

    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233"})
    @DisplayName("findByUsername")
    void getByUsername(@AggregateWith(UserAggregator.class) User user){
        User user1 = userService.getByUsername(user.getUsername()).get(0);
        assertEquals(user, user1);
    }

    @ParameterizedTest
    @CsvSource({"1, zhenya, Evgeniy, Matveev, 0, evg@ewqe, 1234, 213123233"})
    @DisplayName("update")
    void updateUser(@AggregateWith(UserAggregator.class) User user) {
        userService.updateUser(user);
        assertEquals(user, userService.getByUsername(user.getUsername()).get(0));
    }

    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233"})
    @DisplayName("delete")
    void deleteUser(@AggregateWith(UserAggregator.class) User user) {
        userService.deleteUser(user.getId());
        assertEquals(1, userService.getAll().size());
    }

    @Test
    void getAll() {
        assertEquals(2, userService.getAll().size());
    }
}
