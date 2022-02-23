package remora.remora.FrameExtraction.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FrameExtractionRequestDto {
    @NotNull
    @NotEmpty
    public List<String> videoCode;

    public FrameExtractionRequestDto() {
        this.videoCode = new ArrayList<>();
    }
}
