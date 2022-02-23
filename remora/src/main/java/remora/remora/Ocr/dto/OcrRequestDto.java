package remora.remora.Ocr.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class OcrRequestDto {
    @NotEmpty
    @NotNull
    public ArrayList<ArrayList<Integer>> frameSet;
    @NotEmpty
    @NotNull
    public List<String> videoCode;

    public OcrRequestDto(){
        this.frameSet = new ArrayList<>();
        this.videoCode = new ArrayList<>();
    }
}
