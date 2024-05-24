package hac.adderspringbackend;

import dao.ComputeResponse;
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

    @GetMapping("/add/a/{a}/b/{b}")
    public ComputeResponse add(@PathVariable int a, @PathVariable int b) {
        return new ComputeResponse(a + b);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body("Invalid input: " + e.getName() + " parameter of type " + e.getRequiredType().getSimpleName());
    }
}
