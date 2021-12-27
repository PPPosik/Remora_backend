package remora.remora.FrameExtraction.dto;

import java.io.File;

public class FrameExtractionRequestDto {
    public File originVideo;

    public FrameExtractionRequestDto(File originVideo){
        this.originVideo = originVideo;
    }
}
