package programmers.binarySearch;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BorderControlTest {

    BorderControl obj = new BorderControl();

    @Test
    void solution1() {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            assertEquals(28, obj.solution(6, new int[] {7, 10}));
        });
    }

    @Test
    void solution2() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertEquals(14, obj.solution(7, new int[] {3, 7, 10}));
        });
    }

    @Test
    void solution3() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertEquals(15, obj.solution(8, new int[] {3, 7, 10}));
        });
    }
}