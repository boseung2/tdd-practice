package org.tdd.tddpractice.chap02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);

        assert(result == 3);
        assert(Calculator.plus(4, 1) == 5);

    }
}
