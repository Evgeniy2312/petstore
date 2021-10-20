package com.example.swagger.controller;

import com.example.swagger.aggregators.UserAggregator;
import com.example.swagger.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;


@WebMvcTest(UserController.class)
class UserControllerTest {






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
        userController.createUser(user);
        userController.createUser(user2);
    }

    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233",
            "2, zhenya, Zhenya, Matveev, 0, email@ewqe, 12312332, 234111231"
    })
    @DisplayName("addUser")
    void createUser(@AggregateWith(UserAggregator.class) User user) throws Exception {
        userController.createUser(user);
        mockMvc.perform((RequestBuilder) post("/users/addUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(208))
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

    }

    @Test
    void createWithList() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getByUsername() {
    }

    @Test
    void getAll() {
    }
}