package com.codestates.homework;

import com.codestates.helper.RandomPasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomPasswordGeneratorTest {
    @DisplayName("실습 3: 랜덤 패스워드 생성 테스트")
    @Test
    public void generateTest() {
        // TODO 여기에 테스트 케이스를 작성해주세요.

        int Upper = 2;
        int Lower = 5;
        int Num = 4 ;
        int Spc = 2 ;

        String password = RandomPasswordGenerator.generate(Upper, Lower, Num, Spc);

        int expectedTotalLen = password.length();

        int actual = expectedTotalLen-1;

        int UpperCase = password.replaceAll("[^A-Z]", "").length();
        int LowerCase = password.replaceAll("[^a-z]", "").length();
        int NumCase = password.replaceAll("[^0-9]", "").length();
        int SpcCase = password.replaceAll("[^0-9a-zA-Z]", "").length();

        System.out.println(password);


        assertEquals(expectedTotalLen, actual);

        assertEquals(Upper, UpperCase); // 대문자 갯수
        assertEquals(Lower, LowerCase); // 소문자 갯수
        assertEquals(Num, NumCase);  // 숫자 갯수
        assertEquals(Spc, SpcCase);  // 특수문자 갯수

    }
}
