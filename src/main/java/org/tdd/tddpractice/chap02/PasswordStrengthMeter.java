package org.tdd.tddpractice.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        int meetCounts = getMetCriteriaCounts(s);

        if(meetCounts <= 1) return PasswordStrength.WEAK;
        if(meetCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if(s.length() >= 8) metCounts++;
        if(meetsContainingNumberCriteria(s)) metCounts++;
        if(meetsContainingUppercaseCriteria(s)) metCounts++;

        return metCounts;
    }

    private boolean meetsContainingUppercaseCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }

        return false;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }

        return false;
    }
}
