package com.example.swagger.aggregators;

import com.example.swagger.entity.Order;
import com.example.swagger.entity.OrderStatus;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.util.Locale;

public class OrderAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return Order.builder().id(argumentsAccessor.getLong(0))
                .orderStatus(OrderStatus.valueOf(argumentsAccessor.getString(1).toUpperCase(Locale.ENGLISH)))
                .build();
    }
}
