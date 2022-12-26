package com.codestates;
import reactor.core.publisher.Mono;
public class HelloReactiveExample01 {
    public static void main(String[] args) {
//        Mono<String> mono = Mono.just("Hello,Reactive");
//
//        mono.subscribe(message-> System.out.println(message));
        Mono.just("Hello,Reactive").subscribe(message-> System.out.println(message));

    }
}
