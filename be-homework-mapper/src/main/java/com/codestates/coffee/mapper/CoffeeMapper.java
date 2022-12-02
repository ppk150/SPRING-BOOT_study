package com.codestates.coffee.mapper;

import com.codestates.coffee.CoffeePatchDto;
import com.codestates.coffee.CoffeePostDto;
import com.codestates.coffee.CoffeeResponseDto;
import com.codestates.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface  CoffeeMapper {

    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    CoffeeResponseDto coffeeToMemberResponseDto(Coffee coffee);

}
