package remora.remora.FrameExtraction;

import org.springframework.web.bind.annotation.RestController;

import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

@RestController
public class FrameExtractinController {
    FrameExtractionService frameExtractionService = new FrameExtractionService();

    public FrameExtractionResponseDto frameExtract(FrameExtractionRequestDto request) {
        return frameExtractionService.frameExtract(request);
    }
}
