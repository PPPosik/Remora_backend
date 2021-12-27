package remora.remora.Classification.dto;

public class ClassificationRequestDto {
    public String originResultText;
    public String translatedResultText;
    public String language;

    public ClassificationRequestDto(String originResultText, String translatedResultText, String language){
        this.originResultText = originResultText;
        this.translatedResultText = translatedResultText;
        this.language = language;
    }
}
