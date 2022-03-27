package remora.remora.Exception;

public class NotFoundVideoException extends RuntimeException {
    public NotFoundVideoException(){
        super("Video Not Found!");
    }
}
