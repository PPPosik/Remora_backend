package remora.remora.FrameExtraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

@RestController
public class FrameExtractionController {
    private final FrameExtractionService frameExtractionService;

    @Autowired
    public FrameExtractionController(FrameExtractionService frameExtractionService) {
        this.frameExtractionService = frameExtractionService;
    }

    @GetMapping("/frames")
    public FrameExtractionResponseDto frameExtract(@RequestBody FrameExtractionRequestDto request) {
        try {
            return frameExtractionService.frameExtract(request);
        } catch (InterruptedException e) {
            e.printStackTrace();

            FrameExtractionResponseDto response = new FrameExtractionResponseDto();
            response.success = false;
            return response;
        }
    }
}
