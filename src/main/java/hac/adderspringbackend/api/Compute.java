package hac.adderspringbackend.api;

import hac.adderspringbackend.dao.ComputeOperands;
import hac.adderspringbackend.dao.ComputeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/api")
public class Compute {

    @GetMapping("")
    public String getRoot() {
        return "Hello World!";
    }

    /**
     * Add two numbers
     * @param a
     * @param b
     * @return
     */
    @GetMapping("/add/a/{a}/b/{b}")
    public ComputeResponse add(@PathVariable int a, @PathVariable int b) {
        return new ComputeResponse(a + b);
    }

    /**
     * Add two numbers
     * @param operands a dto object containing the operands
     * @return the result of the addition
     */
    @PostMapping("/add")
    public ComputeResponse addPost(@RequestBody ComputeOperands operands) {
        return new ComputeResponse(operands.getOperand1() + operands.getOperand2());
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest()
                .body("Invalid input: " + e.getName()
                        + " parameter of type " + e.getRequiredType().getSimpleName());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.internalServerError()
                .body("Internal server error: " + e.getMessage());
    }
}
