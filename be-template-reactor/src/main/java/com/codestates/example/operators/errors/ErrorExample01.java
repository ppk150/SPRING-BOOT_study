package com.codestates.example.operators.errors;


import com.codestates.coffee.entity.Coffee;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorExample01 {
    public static void main(String[] args) {
        Mono.justOrEmpty(findVerifiedCoffee())
                .switchIfEmpty(Mono.error(new RuntimeException("Nont found coffee")))
                .subscribe(
                        data -> log.info("{} : {}",data.getKorName(),data.getPrice()),
                        error -> log.info("# onError: {}", error.getMessage())
                );
    }

    private static  Coffee findVerifiedCoffee(){
        return null;
    }
}
