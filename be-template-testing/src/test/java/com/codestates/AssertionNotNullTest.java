package com.codestates;
import com.codestates.CryptoCurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertionNotNullTest {

    @DisplayName("AssertionNull() Test")
    @Test
    public void assertNotNullTest(){
        String currencyName = getCryptoCurrency("ETH");
//        assertNotNull(currencyName , "shold be not null");
//        assertThat(currencyName, is(notNullValue()));
        assertThat(currencyName, is(nullValue()));
    }


    private String getCryptoCurrency(String unit){
        return CryptoCurrency.map.get(unit);

    }
}
