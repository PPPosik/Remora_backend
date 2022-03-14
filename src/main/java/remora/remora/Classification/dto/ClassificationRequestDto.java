package remora.remora.Classification.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ClassificationRequestDto {
    @NotNull
    @NotEmpty
    public List<String> translatedText;

    public ClassificationRequestDto() {
        this.translatedText = new ArrayList<>();
    }
}
