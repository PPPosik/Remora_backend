package remora.remora.Translation.dto;

public class TranslationRequestDto {
    public String originText;
    public String language;
    public Boolean needTranslation;
    
    public TranslationRequestDto(StringBuffer originText, String language, Boolean needTranslation){
        this.originText = originText.toString();
        this.language = language;
        this.needTranslation = needTranslation;
    }
}
