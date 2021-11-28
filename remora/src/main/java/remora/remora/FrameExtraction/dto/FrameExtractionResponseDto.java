package remora.remora.FrameExtraction.dto;

import java.util.ArrayList;

import org.jcodec.common.model.Picture;

public class FrameExtractionResponseDto {
    public boolean success;
    public ArrayList<Picture> frameSet;
}
