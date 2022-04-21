package remora.remora.Ocr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import remora.remora.Exception.RequestDataLengthDifferentException;
import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

import javax.validation.Valid;

@RestController
public class OcrController {
    private final OcrService ocrService;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping(value = "/text", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OcrResponseDto detection(@RequestBody @Valid OcrRequestDto request) {
        OcrResponseDto response = new OcrResponseDto();

        try {
            if (request.frameSet.size() != request.videoCode.size()) {
                log.error("Request data length different. frameSet length : {}, videoCode : {}", request.frameSet.size(), request.videoCode.size());
                throw new RequestDataLengthDifferentException("frameSet length : " + request.frameSet.size() + ", videoCode length : " + request.videoCode.size());
            }

            for (int i = 0; i < request.videoCode.size(); i++) {
                response.text.add(ocrService.detection(request.frameSet.get(i), request.videoCode.get(i)));
            }
            log.info("Frame extraction is {}", "success");
            response.success = true;
            response.message = "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Frame extraction is {}, Exception : {}", "fail", e.getMessage());
            response.success = false;
            response.message = e.toString();
            response.text = null;
        }

        return response;
    }
}
