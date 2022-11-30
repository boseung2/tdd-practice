package org.tdd.tddpractice.chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PasswordStrengthMeterTest {
    private final PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertThat(result).isEqualTo((expStr));
    }

    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이여야 함")
    void test1() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이가 8 미만이고 나머지 조건은 충족")
    void test2() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족")
    void test3() {
        assertStrength("ab!ABqwert", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("유효하지 않은 암호")
    void test4() {
       assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void 빈_문자열() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 대문자를_포함하지_않고_나머지_조건을_충족() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void 길이가_8글자_이상인_조건만_충족() {
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    void 숫자_포함_조건만_충족() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    void 대문자_포함_조건만_충족() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    @Test
    void 아무_조건도_충족하지_않음() {
        assertStrength("abc", PasswordStrength.WEAK);
    }

}

