package com.codestates.order.entity;

import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderCoffeeId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name= "COFFEE_ID")
    private Coffee coffee;

    public void addCoffee(Coffee coffee){
        this.coffee = coffee;
    }

    public void addOrder(Order order){
        this.order = order;
    }

}
