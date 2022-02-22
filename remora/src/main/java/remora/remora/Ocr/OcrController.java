package remora.remora.Ocr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

@RestController
public class OcrController {
    private final OcrService ocrService;

    @Autowired
    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @GetMapping("/text")
    @ResponseBody
    public OcrResponseDto detection(@RequestBody OcrRequestDto request) {
        OcrResponseDto response = new OcrResponseDto();

        try {
            for (int i = 0; i < request.videoCode.size(); i++) {
                response.text.add(ocrService.detection(request.frameSet.get(i), request.videoCode.get(i)));
            }

            response.success = true;
            response.message = "success";
        } catch (Exception e) {
            e.printStackTrace();

            response.success = false;
            response.message = e.toString();
            response.text = null;
        }

        return response;
    }
}
