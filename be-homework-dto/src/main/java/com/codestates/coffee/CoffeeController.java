package com.codestates.coffee;

import com.codestates.member.MemberPatchDto;
import com.codestates.member.MemberPostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    // 1. DTO 클래스 및 유효성 검증을 적용하세요.


    // 가격 부분 완료
    // 한글 커피명 필요
    // 영어 커피명 필요

    @PostMapping
    public ResponseEntity postCoffe(
                                    @Valid @RequestBody CoffeePostDto coffeeDto) {

        //coffeeDto.setPrice(price);
        return new ResponseEntity<>(coffeeDto, HttpStatus.CREATED);
    }

    // 2. DTO 클래스 및 유효성 검증을 적용하세요.
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Min(1) long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);


        // No need Business logic

        return new ResponseEntity<>(coffeePatchDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        // No need business logic

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
