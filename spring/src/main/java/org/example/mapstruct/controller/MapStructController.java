package org.example.mapstruct.controller;

import org.example.mapstruct.one2one.Car;
import org.example.mapstruct.one2one.CarDto;
import org.example.mapstruct.one2one.CarMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MapStructController
 *
 * @author: Diammd
 * @since: 2023/8/7
 */
@RestController
@RequestMapping("mapstruct")
public class MapStructController {

    @Resource
    CarMapper carMapper;

    @GetMapping("one2one")
    public CarDto test1(){
        Car car = new Car("M5", "BMW", 10);
        CarDto carDto = carMapper.carConvert(car);
        return carDto;
    }
}
