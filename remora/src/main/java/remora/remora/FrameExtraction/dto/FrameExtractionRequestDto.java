package remora.remora.FrameExtraction.dto;

import java.util.ArrayList;
import java.util.List;

public class FrameExtractionRequestDto {
    public List<String> videoCode;

    public FrameExtractionRequestDto() {
        this.videoCode = new ArrayList<>();
    }
}
