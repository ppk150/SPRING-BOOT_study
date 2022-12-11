package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloJUnitTest {

    @DisplayName("Hello Junit Test using hamcrest")
    @Test
    public void assertionTest(){
        String expected = "Hello, JUnit";
        String actual = "Hello, JUnt";

//        assertEquals(expected,actual);
        assertThat(actual, is(equalTo(expected)));


    }
}
