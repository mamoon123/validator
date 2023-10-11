package akirolab.service.validator.controller;

import akirolab.service.validator.util.algorithm.TokenValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenValidatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenValidator tokenValidator;

    @BeforeEach
    public void setup() {
        when(tokenValidator.validate(any())).thenReturn(false);
        when(tokenValidator.validate("1234-5678-1234-5678")).thenReturn(true);
    }

    @Test
    public void testValidTokenFormatAndValidLuhn() throws Exception {
        String requestBody = "{ \"token\": \"4111-1111-1111-1111\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isValid").value(true));
    }
    @Test
    public void testInvalidTokenFormat() throws Exception {
        String requestBody = "{ \"token\": \"1234-5678-1234-5678\" }"; // Correct the request body

        mockMvc.perform(MockMvcRequestBuilders.post("/api/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isValid").value(false));
    }
}
