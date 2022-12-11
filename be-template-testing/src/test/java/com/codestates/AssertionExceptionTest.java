package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.codestates.CryptoCurrency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertionExceptionTest {

    @DisplayName("throws NullPointerException when map.get()")
    @Test
    public void assertionThrowExceptionTest(){
//        assertThrows(NullPointerException.class, () -> getCryptoCurrency("XRP"));
        Throwable actual = assertThrows(NullPointerException.class, () -> getCryptoCurrency("XRP"));
        assertThat(actual.getCause(),is(equalTo(null)));
    }

    private String getCryptoCurrency(String unit){
        return CryptoCurrency.map.get(unit).toUpperCase();
    }


}
