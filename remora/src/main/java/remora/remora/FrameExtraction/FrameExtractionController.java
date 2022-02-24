package remora.remora.FrameExtraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

import javax.validation.Valid;

@RestController
public class FrameExtractionController {
    private final FrameExtractionService frameExtractionService;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public FrameExtractionController(FrameExtractionService frameExtractionService) {
        this.frameExtractionService = frameExtractionService;
    }

    @GetMapping("/frames")
    @ResponseBody
    public FrameExtractionResponseDto frameExtract(@RequestBody @Valid FrameExtractionRequestDto request) {
        FrameExtractionResponseDto response = new FrameExtractionResponseDto();

        try {
            for (String path : request.videoCode) {
                response.frameSet.add(frameExtractionService.frameExtract(path));
            }
            log.info("Frame extraction is {}", "success");
            response.success = true;
            response.message = "Success";
            response.videoCode = request.videoCode;

        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Frame extraction is {}, Exception : {}", "fail", e.getMessage());
            response.success = false;
            response.message = e.getMessage();
            response.frameSet = null;
            response.videoCode = null;
        }

        return response;
    }
}
