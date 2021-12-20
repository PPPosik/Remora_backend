package remora.remora.Ocr;

import org.springframework.stereotype.Service;
import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

import java.awt.Image;
import java.util.List;

@Service
public class OcrService {
    public OcrRequestDto ocrProcess(OcrRequestDto request){
        /*
            To do : OCR Process
         */
        return new OcrRequestDto();
    }

    public OcrRequestDto preProcessing(OcrRequestDto request){
        /*
            To do : PreProcessing
         */
        return new OcrRequestDto();
    }

    public List<Image> detection(OcrRequestDto request){
        /*
            To do : Detection
         */
        return List.of();
    }

    public void recognition(){
        /*
            To do : Recognition
         */
    }

    public OcrResponseDto PostProcessing(List<String> request){
        /*
            To do : PostProcessing
         */
        return new OcrResponseDto();
    }
}
