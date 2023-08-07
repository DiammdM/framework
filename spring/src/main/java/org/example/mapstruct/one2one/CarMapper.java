package org.example.mapstruct.one2one;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "type", target = "carType")
    CarDto carConvert(Car car);
}
