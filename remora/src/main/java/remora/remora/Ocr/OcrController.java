package remora.remora.Ocr;

import org.springframework.web.bind.annotation.RestController;
import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

import java.awt.Image;
import java.util.List;

@RestController
public class OcrController {
    private OcrService ocrService = new OcrService();

    public OcrRequestDto ocrProcess(OcrRequestDto request){
        /*
            To do : OcrService Call
         */
        return ocrService.ocrProcess(request);
    }

    public OcrRequestDto preProcessing(OcrRequestDto request){
        /*
            To do : OcrService Call
         */
        return ocrService.preProcessing(request);
    }

    public List<Image> detection(OcrRequestDto request){
        /*
            To do : OcrService Call
         */
        return ocrService.detection(request);
    }

    public void recognition(){
        /*
            To do : OcrService Call
         */
        ocrService.recognition();
    }

    public OcrResponseDto postProcessing(List<String> request){
        /*
            To do : OcrService Call
         */
        return ocrService.PostProcessing(request);
    }
}
