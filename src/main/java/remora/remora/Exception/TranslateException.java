package remora.remora.Exception;

public class TranslateException extends RuntimeException {
    public TranslateException(){
        super("Translate fail");
    }
    public TranslateException(String s){
        super(s);
    }
}
