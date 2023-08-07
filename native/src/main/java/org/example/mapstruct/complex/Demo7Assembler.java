package org.example.mapstruct.complex;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Demo7Assembler {

    Demo7Assembler INSTANCE = Mappers.getMapper(Demo7Assembler.class);

    @Mapping(source = "productId", target = "productDetail.productId")
    @Mapping(source = "productDetail.id", target = "productDetail.detailId")
    ProductDTO toDTO(Product product);
}
