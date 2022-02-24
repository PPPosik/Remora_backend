package remora.remora.Upload.exception;

public class NotFoundVideoException extends RuntimeException {
    public NotFoundVideoException(){
        super("Video Not Found!");
    }
}
