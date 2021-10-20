package com.example.swagger.repository;


import com.example.swagger.aggregators.UserAggregator;
import com.example.swagger.entity.User;
import org.hibernate.annotations.Source;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {


    private final UserDao userDao;

    @Autowired
    public UserRepositoryTest(UserDao userDao) {
        this.userDao = userDao;
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
        userDao.save(user);
        userDao.save(user2);
    }


    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233"})
    @DisplayName("findByUsername")
    public void findByUsername(@AggregateWith(UserAggregator.class) User user) {
        List<User> users = userDao.getUsersByUsername(user.getUsername());
        assertEquals(user.getUsername(), users.get(0).getUsername());
    }


    @Test
    @DisplayName("findAll")
    public void findAll(){
        assertEquals(2, userDao.findAll().size());
    }

    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233",
            "2, zhenya, Zhenya, Matveev, 0, email@ewqe, 12312332, 234111231"
    })
    @DisplayName("findById")
    public void findByIdTest(@AggregateWith(UserAggregator.class) User user){
        Optional<User> user1 = userDao.findById(user.getId());
        assertEquals(user, user1.get());
    }


    @ParameterizedTest
    @CsvSource({"1, peter, Peter, Josh, 1, peter@ewqe, 1234, 213123233"})
    @DisplayName("delete")
    public void delete(@AggregateWith(UserAggregator.class) User user){
        userDao.delete(user);
        assertEquals(1, userDao.findAll().size());
    }


    @ParameterizedTest
    @CsvSource({"1, zhenya, Evgeniy, Matveev, 0, evg@ewqe, 1234, 213123233"})
    @DisplayName("update")
    public void update(@AggregateWith(UserAggregator.class) User user){
        userDao.save(user);
        assertEquals(user, userDao.getUsersByUsername(user.getUsername()).get(0));
    }




}
