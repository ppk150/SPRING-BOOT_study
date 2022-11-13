package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {


    @PostMapping
    public ResponseEntity postOrder(@RequestParam("engName") String engName,
                                    @RequestParam("korName") String korName,
                                    @RequestParam("price") String price) {


        Map<String, String> map = new HashMap<>();
        map.put("engName", engName);
        map.put("korName", korName);
        map.put("price", price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId){
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees(){
        System.out.println("# get Coffee");

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
