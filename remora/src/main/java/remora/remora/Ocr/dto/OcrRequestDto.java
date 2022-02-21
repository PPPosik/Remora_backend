package remora.remora.Ocr.dto;

import java.util.ArrayList;
import java.util.List;

public class OcrRequestDto {
    public ArrayList<ArrayList<Integer>> frameSet;
    public List<String> videoCode;

    public OcrRequestDto(){
        this.frameSet = new ArrayList<>();
        this.videoCode = new ArrayList<>();
    }
}
