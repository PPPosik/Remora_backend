package remora.remora.MainControl;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import remora.remora.FrameExtraction.FrameExtractinController;
import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

@RestController
public class MainController {
    @GetMapping("/test/extraction")
    public void testExtraction() {
        FrameExtractinController frameExtractionController = new FrameExtractinController();
        FrameExtractionRequestDto request = new FrameExtractionRequestDto();
        FrameExtractionResponseDto response = null;

        request.originVideo = new File("C:\\testVideo3.mp4");
        response = frameExtractionController.frameExtract(request);
        if(response.success) {
            System.out.println("Success frame extraction");
        }
    }
}
