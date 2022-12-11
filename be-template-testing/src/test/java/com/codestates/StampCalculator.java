package com.codestates;

import com.codestates.order.entity.Order;

public class StampCalculator {
    public static int claculateStampCount(int nowCount, int earned){
        return nowCount + earned;
    }

    public static int calculateEarnedStampCount(Order order){

        return order.getOrderCoffees().stream()
                .map(orderCoffee -> orderCoffee.getQuantity())
                .mapToInt(quantity -> quantity)
                .sum();

    }

}
