package remora.remora.Exception;

public class RequestDataLengthDifferentException extends RuntimeException {
    public RequestDataLengthDifferentException() {
        super("Request Data length is different");
    }

    public RequestDataLengthDifferentException(String s) {
        super(s);
    }
}
