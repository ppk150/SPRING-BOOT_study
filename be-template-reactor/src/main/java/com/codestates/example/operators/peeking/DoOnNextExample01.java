package com.codestates.example.operators.peeking;


import com.codestates.coffee.entity.Coffee;
import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
@Slf4j
public class DoOnNextExample01 {
    public static void main(String[] args) {
        Flux.fromIterable(SampleData.coffeeList)
                .doOnNext(coffee -> validateCoffee(coffee))
                .subscribe(data -> log.info("{} : {}",data.getKorName(), data.getPrice()));
    }

    private static void validateCoffee(Coffee coffee){
        if(coffee == null){
            throw new RuntimeException("not found coffee");
        }
    }
}
