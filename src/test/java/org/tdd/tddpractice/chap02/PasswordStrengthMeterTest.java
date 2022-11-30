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
        assertThat(result).isEqualTo(expStr);
    }

    @Test
    void 암호가_모든_조건을_충족하면_암호_강도는_강함() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    void 길이가_8미만이고_나머지_조건은_충족() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    void 숫자를_포함하지_않고_나머지_조건은_충족() {
        assertStrength("ab!ABqwert", PasswordStrength.NORMAL);
    }

    @Test
    void 유효하지_않은_암호() {
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

