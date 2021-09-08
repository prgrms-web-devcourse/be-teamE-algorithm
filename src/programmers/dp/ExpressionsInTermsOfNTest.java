package programmers.dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionsInTermsOfNTest {
    ExpressionsInTermsOfN obj = new ExpressionsInTermsOfN();

    @Test
    void solution1() {
        assertEquals(4, obj.solution(5, 12));
    }

    @Test
    void solution2() {
        assertEquals(3, obj.solution(2, 11));
    }
}