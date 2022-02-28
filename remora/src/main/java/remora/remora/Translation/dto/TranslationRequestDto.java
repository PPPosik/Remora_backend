package remora.remora.Translation.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class TranslationRequestDto {
    @NotEmpty
    @NotNull
    public List<String> text;
    @NotEmpty
    @NotNull
    public List<Boolean> needTranslation;

    public TranslationRequestDto() {
        this.text = new ArrayList<>();
        this.needTranslation = new ArrayList<>();
    }
}
