package remora.remora.FrameExtraction.dto;

import java.util.ArrayList;

import remora.remora.Common.PicturePair;

public class FrameExtractionResponseDto {
    public Boolean success;
    public String message;
    public ArrayList<ArrayList<PicturePair>> frameSet;
}
