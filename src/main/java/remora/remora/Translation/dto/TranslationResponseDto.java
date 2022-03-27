package remora.remora.Translation.dto;

import java.util.ArrayList;
import java.util.List;

public class TranslationResponseDto {
    public boolean success;
    public String message;
    public List<String> text;
    public List<String> translatedText;

    public TranslationResponseDto() {
        this.success = false;
        this.message = null;
        this.text = new ArrayList<>();
        this.translatedText = new ArrayList<>();
    }
}
