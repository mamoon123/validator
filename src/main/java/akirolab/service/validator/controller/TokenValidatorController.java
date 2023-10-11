package akirolab.service.validator.controller;

import akirolab.service.validator.dtos.TokenValidationRequestDto;
import akirolab.service.validator.util.algorithm.LuhnAlgorithm;
import akirolab.service.validator.util.algorithm.TokenValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenValidatorController {

    private final TokenValidator tokenValidator = new LuhnAlgorithm();

    @PostMapping("/api/validate")
    public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody TokenValidationRequestDto requestDTO) {
        String token = requestDTO.getToken();
        boolean isValid = tokenValidator.validate(token);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isValid", isValid);
        return ResponseEntity.ok(response);
    }
}