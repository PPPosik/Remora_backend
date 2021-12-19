package remora.remora.FrameExtraction;

import org.springframework.web.bind.annotation.RestController;

import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

@RestController
public class FrameExtractionController {
    private FrameExtractionService frameExtractionService = new FrameExtractionService();

    public FrameExtractionResponseDto frameExtract(FrameExtractionRequestDto request) {
        try {
            return frameExtractionService.frameExtract(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
