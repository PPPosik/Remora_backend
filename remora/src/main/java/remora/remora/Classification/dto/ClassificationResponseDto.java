package remora.remora.Classification.dto;

import java.util.ArrayList;
import java.util.List;

public class ClassificationResponseDto {
    public boolean success;
    public String message;
    public List<String> translatedText;
    public List<List<String>> keywords;

    public ClassificationResponseDto() {
        this.success = false;
        this.message = null;
        this.translatedText = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }
}
