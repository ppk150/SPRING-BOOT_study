package com.codestates.coffee;
import com.codestates.member.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

public class CoffeePatchDto {

    private long coffeeId;

    @NotSpace(message = "커피 이름은 공백이 아니어야 합니다.")
    private String korName;

    @NotSpace(message = "커피 이름은 공백이 아니어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z](\\s?[a-zA-Z])*$")
    private String engName;

    @Range(min = 100, max = 50000)
    private int price;

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
