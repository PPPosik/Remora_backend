package remora.remora.FrameExtraction.dto;

import java.util.ArrayList;
import java.util.List;

public class FrameExtractionResponseDto {
    public Boolean success;
    public String message;
    public ArrayList<ArrayList<Integer>> frameSet;
    public List<String> videoCode;

    public FrameExtractionResponseDto() {
        this.success = false;
        this.message = null;
        this.frameSet = new ArrayList<>();
        this.videoCode = new ArrayList<>();
    }
}
