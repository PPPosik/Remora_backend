package remora.remora.Ocr.dto;

import java.util.ArrayList;
import java.util.List;

public class OcrResponseDto {
    public boolean success;
    public String message;
    public List<String> text;

    public OcrResponseDto() {
        this.success = false;
        this.message = null;
        this.text = new ArrayList<>();
    }
}
