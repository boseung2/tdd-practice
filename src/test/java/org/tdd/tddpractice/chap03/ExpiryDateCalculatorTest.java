package org.tdd.tddpractice.chap03;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                LocalDate.of(2019, 3, 1), 10_000,
                LocalDate.of(2019,4,1));

        assertExpiryDate(
                LocalDate.of(2019, 5, 5), 10_000,
                LocalDate.of(2019,6,5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                LocalDate.of(2019, 1, 31), 10_000,
                LocalDate.of(2019,2,28)
        );

        assertExpiryDate(
                LocalDate.of(2019, 5, 31), 10_000,
                LocalDate.of(2019, 6, 30)
        );

        assertExpiryDate(
                LocalDate.of(2020, 1, 31), 10_000,
                LocalDate.of(2020,2,29)
        );
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertThat(realExpiryDate).isEqualTo(expectedExpiryDate);
    }

}
