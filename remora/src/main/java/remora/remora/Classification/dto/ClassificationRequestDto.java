package remora.remora.Classification.dto;

public class ClassificationRequestDto {
    public String originResultText;
    public String translatedResultText;
    public String language;

    public ClassificationRequestDto(StringBuffer originResultText, String translatedResultText, String language){
        this.originResultText = originResultText.toString();
        this.translatedResultText = translatedResultText;
        this.language = language;
    }
}
