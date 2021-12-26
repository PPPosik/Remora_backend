package remora.remora.Ocr;

import org.springframework.stereotype.Service;

import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

@Service
public class OcrService {
    public OcrResponseDto detection(OcrRequestDto request) {
        OcrResponseDto response = new OcrResponseDto();
        response.success = false;
        response.originResultText = null;

        ;

        return response;
    }
}
