package remora.remora.FrameExtraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public FrameExtractionResponseDto frameExtract(@RequestBody FrameExtractionRequestDto request) {
        FrameExtractionResponseDto response = new FrameExtractionResponseDto();

        try {
            for (String path : request.videoCode) {
                response.frameSet.add(frameExtractionService.frameExtract(path));
            }

            response.success = true;
            response.message = "success";
            response.videoCode = request.videoCode;
        } catch (Exception e) {
            e.printStackTrace();

            response.success = false;
            response.message = e.toString();
            response.frameSet = null;
            response.videoCode = null;
        }

        return response;
    }
}
