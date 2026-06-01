package hac.adderspringbackend.api;

import tools.jackson.databind.ObjectMapper;
import hac.adderspringbackend.dto.ComputeOperands;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Compute.class)
class ComputeTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addViaPath_returnsSum() throws Exception {
        mvc.perform(get("/api/add/a/3/b/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(7));
    }

    @Test
    void addViaPath_nonIntegerOperand_returns400() throws Exception {
        mvc.perform(get("/api/add/a/abc/b/4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid input")));
    }

    @Test
    void addViaBody_returnsSumWithMessage() throws Exception {
        ComputeOperands ops = new ComputeOperands();
        ops.setOperand1(10);
        ops.setOperand2(15);

        mvc.perform(post("/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ops)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(25))
                .andExpect(jsonPath("$.message").value("Addition successful!"));
    }

    @Test
    void addViaBody_missingBody_returns500() throws Exception {
        // Current behavior: the catch-all Exception handler intercepts
        // HttpMessageNotReadableException, so this surfaces as 500 rather than 400.
        mvc.perform(post("/api/add").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}