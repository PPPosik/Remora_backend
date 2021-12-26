package remora.remora.Ocr;

import org.springframework.web.bind.annotation.RestController;

import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

@RestController
public class OcrController {
    OcrService ocrService = new OcrService();

    public OcrResponseDto detection(OcrRequestDto request) {
        return ocrService.detection(request);
    }
}
