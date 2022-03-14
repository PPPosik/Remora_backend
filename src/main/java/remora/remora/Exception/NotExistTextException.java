package remora.remora.Exception;

public class NotExistTextException extends RuntimeException{
    public NotExistTextException(){
        super("There is no text in video");
    }
}
