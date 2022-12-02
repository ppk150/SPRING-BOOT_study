package com.codestates.coffee.service;

import com.codestates.coffee.CoffeeController;
import com.codestates.coffee.entity.Coffee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    public Coffee createCoffee(Coffee coffee) {
        // TODO should business logic

        // TODO member 객체는 나중에 DB에 저장 후, 되돌려 받는 것으로 변경 필요.
        Coffee createCoffee = coffee;
        return createCoffee;
    }

    public Coffee updateCoffee(Coffee coffee) {
        // TODO should business logic

        // member 객체는 나중에 DB에 업데이트 후, 되돌려 받는 것으로 변경 필요.
        Coffee updateCoffee = coffee;
        return updateCoffee;
    }

    public Coffee findCoffee(long coffeeId) {
        // TODO should business logic

        // TODO member 객체는 나중에 DB에서 조회 하는 것으로 변경 필요.
        Coffee coffee =
                new Coffee(coffeeId, "아메리카노", "Americano", 2500);
        return coffee;
    }

    public List<Coffee> findCoffees() {
        // TODO should business logic

        // TODO member 객체는 나중에 DB에서 조회하는 것으로 변경 필요.
        List<Coffee> coffees = List.of(
                new Coffee(1, "아메리카노", "Americano", 2500),
                new Coffee(2, "카라멜 라떼", "Caramel Latte", 5000)
        );
        return coffees;
    }

    public void deleteCoffee(long coffeeId) {
        // TODO should business logic
    }
}
