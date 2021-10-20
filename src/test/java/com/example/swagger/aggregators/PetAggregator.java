package com.example.swagger.aggregators;

import com.example.swagger.entity.Pet;
import com.example.swagger.entity.PetStatus;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.util.Locale;

public class PetAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return Pet.builder().id(argumentsAccessor.getLong(0))
                .name(argumentsAccessor.getString(1))
                .petStatus(PetStatus.valueOf(argumentsAccessor.getString(2).toUpperCase(Locale.ENGLISH))).build();
    }
}
