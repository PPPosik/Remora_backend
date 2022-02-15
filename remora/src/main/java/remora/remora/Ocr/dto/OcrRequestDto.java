package remora.remora.Ocr.dto;

import java.util.ArrayList;

public class OcrRequestDto {
    public ArrayList<Integer> frameSet;

    public OcrRequestDto(ArrayList<Integer> frameSet){
        this.frameSet = frameSet;
    }
}
