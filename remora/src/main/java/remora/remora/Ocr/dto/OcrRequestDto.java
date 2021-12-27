package remora.remora.Ocr.dto;

import java.util.ArrayList;

import remora.remora.Common.PicturePair;

public class OcrRequestDto {
    public ArrayList<PicturePair> frameSet;

    public OcrRequestDto(ArrayList<PicturePair> frameSet){
        this.frameSet = frameSet;
    }
}
