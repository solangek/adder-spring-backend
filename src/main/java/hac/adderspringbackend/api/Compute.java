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
     * Add two numbers using path parameters
     * @param a
     * @param b
     * @return
     */
    @GetMapping("/add/a/{a}/b/{b}")
    public ComputeResponse add(@PathVariable int a, @PathVariable int b) {
        return new ComputeResponse(a + b);
    }

    /**
     * Add two numbers using body parameters
     * @param operands a dto object containing the operands
     * @return the result of the addition
     */
    @PostMapping("/add")
    public ComputeResponse addPost(@RequestBody ComputeOperands operands) {
        return new ComputeResponse(
                operands.getOperand1() + operands.getOperand2(), "Addition successful!"
        );
    }


    /**
     * Handle the case where the user provides a wrong type of parameter
     * returns a 400 BAD REQUEST in case of a type mismatch
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest()
                .body("Invalid input: " + e.getName()
                        + " parameter of type " + e.getRequiredType().getSimpleName());
    }

    /**
     * Handle all other exceptions, returns a 500 INTERNAL SERVER ERROR
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.internalServerError()
                .body("Internal server error: " + e.getMessage());
    }
}
