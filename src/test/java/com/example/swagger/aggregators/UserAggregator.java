package com.example.swagger.aggregators;

import com.example.swagger.entity.User;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;



public class UserAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return User.builder().id(argumentsAccessor.getLong(0)).username(argumentsAccessor.getString(1))
                .firstName(argumentsAccessor.getString(2))
                .lastName(argumentsAccessor.getString(3))
                .userStatus(argumentsAccessor.getInteger(4))
                .email(argumentsAccessor.getString(5))
                .password(argumentsAccessor.getString(6))
                .phone(argumentsAccessor.getString(7))
                .build();
    }
}
