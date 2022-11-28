package com.codestates;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(nullable = false, length = 100)
    private String korName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(length = 5, nullable = false)
    private int price;

    @Column(nullable = false, length = 3, unique = true)
    private String coffeeCode;

    //1
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;


    @Column(nullable = false)
    private LocalDateTime createdAt =LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();


    //2
    public enum CoffeeStatus{
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;

        CoffeeStatus(String status){
            this.status = status;
        }

    }

}
