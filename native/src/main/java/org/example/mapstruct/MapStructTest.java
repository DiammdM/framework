package org.example.mapstruct;

import org.example.mapstruct.one2one.Car;
import org.example.mapstruct.one2one.CarDto;
import org.example.mapstruct.one2one.CarMapper;

/**
 * MapStructTest
 *
 * @author: Diammd
 * @since: 2023/8/7
 */
public class MapStructTest {

  public static void main(String[] args) {
    Car car = new Car("M5", "BMW", 10);
    CarDto carDto = CarMapper.INSTANCE.carConvert(car);
    System.out.println(carDto);
  }
}
