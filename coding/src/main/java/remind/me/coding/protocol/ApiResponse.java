package remind.me.coding.protocol;

import org.springframework.http.HttpStatus;

public class ApiResponse <T> {
    private T response;
    private int statusCode;
    private String message;
    public ApiResponse(T response, int statusCode, String message){
        this.response = response;
        this.statusCode = statusCode;
        this.message = message;
    }
    public ApiResponse(T response){
        this.response = response;
        this.statusCode = HttpStatus.OK.value();
        this.message = "OK";
    }
    public ApiResponse(){
        this.statusCode = HttpStatus.OK.value();
        this.message="OK";
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
