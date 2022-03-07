package remora.remora.Exception;

public class NotSupportedLanguageException extends RuntimeException{
    public NotSupportedLanguageException() {
        super("This Language is not supported");
    }

    public NotSupportedLanguageException(String message) {
        super(message);
    }
}
