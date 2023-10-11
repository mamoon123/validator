package akirolab.service.validator.util.algorithm;

import akirolab.service.validator.util.algorithm.TokenValidator;

public class LuhnAlgorithm implements TokenValidator {
    private static final String PATTERN = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$";

    @Override
    public boolean validate(String token) {
        if (!token.matches(PATTERN)) {
            return false;
        }

        String cleanedToken = token.replaceAll("[\\s-]+", "");

        int nDigits = cleanedToken.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = cleanedToken.charAt(i) - '0';
            if (isSecond) {
                d = d * 2;
                if (d > 9) {
                    d = d - 9;
                }
            }
            nSum += d;
            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}