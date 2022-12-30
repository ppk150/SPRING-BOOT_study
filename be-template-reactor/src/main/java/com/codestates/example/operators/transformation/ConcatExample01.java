package com.codestates.example.operators.transformation;

import com.codestates.example.operators.sample_data.SampleData;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ConcatExample01 {
    public static void main(String[] args) {
        Flux
                .concat(Flux.fromIterable(SampleData.salesOfCafeA),
                        Flux.fromIterable(SampleData.salesOfCafeC),
                        Flux.fromIterable(SampleData.salesOfCafeB)

                )
                .reduce((a,b) -> a+b)
                .subscribe(data -> log.info("# total sales: {}" ,data));
    }
}
