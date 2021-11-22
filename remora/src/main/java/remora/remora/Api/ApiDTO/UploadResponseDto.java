package remora.remora.Api.ApiDTO;

import java.util.List;

public class UploadResponseDto {
    Boolean success;
    String message;
    int code;
    List<String> originResultText;
    List<String> translatedResultText;
    List<String> keywords;
    Boolean needTranslation;
}
