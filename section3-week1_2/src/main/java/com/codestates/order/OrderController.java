package com.codestates.order;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @PostMapping
    public String postOrder(@RequestParam("memberId") long memberId,
                            @RequestParam("coffeeId") long coffeeId) {

        System.out.println("# memberId: " + memberId);
        System.out.println("# coffeeId: " + coffeeId);

        String response =
                "{\""+
                        "memberId\":\"" + memberId + "\"," +
                        "\"coffeeId\":\"" + coffeeId + "\"," +
                        "}";
        return response;
    }

    @GetMapping("/{order-id}")
    public String getOrder(@PathVariable("order-id") long orderId){
        System.out.println("# orderId: " + orderId);

        return null;
    }

    @GetMapping
    public String getOders(){
        System.out.println("# get Orders");

        return null;
    }


}
