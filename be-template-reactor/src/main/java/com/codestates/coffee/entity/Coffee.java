package com.codestates.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Coffee {


    private String korName;
    private String engName;
    private int price;
    private String coffeeCode;
}
