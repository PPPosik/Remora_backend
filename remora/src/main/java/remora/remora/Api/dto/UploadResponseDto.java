package remora.remora.Api.dto;

import java.util.List;

public class UploadResponseDto {
    public Boolean success;
    public String message;
    public int code;
    public List<String> originResultText;
    public List<String> translatedResultText;
    public List<String> keywords;
    public Boolean needTranslation;
}
