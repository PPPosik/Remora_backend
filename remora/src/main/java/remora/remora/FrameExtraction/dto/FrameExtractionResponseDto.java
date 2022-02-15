package remora.remora.FrameExtraction.dto;

import java.util.ArrayList;

public class FrameExtractionResponseDto {
    public Boolean success;
    public String message;
    public ArrayList<ArrayList<Integer>> frameSet;

    public FrameExtractionResponseDto() {
        this.success = false;
        this.message = null;
        this.frameSet = new ArrayList<>();
    }
}
