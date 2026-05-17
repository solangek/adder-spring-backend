package hac.adderspringbackend.dto;

public class ComputeResponse {

    private int result;
    private String message;

    public ComputeResponse(int result) {
        this.result = result;
    }

    public ComputeResponse(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
