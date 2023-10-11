package akirolab.service.validator.util.algorithm;

@FunctionalInterface
public interface TokenValidator {
    boolean validate(String token);
}