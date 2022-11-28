package com.codestates;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v11/orders")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final OrderMapp

}
