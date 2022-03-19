package remora.remora.Classification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import remora.remora.Classification.dto.ClassificationRequestDto;
import remora.remora.Classification.dto.ClassificationResponseDto;

import javax.validation.Valid;

@Controller
public class ClassificationController {
    private final ClassificationService classificationService;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping("/keywords")
    @ResponseBody
    public ClassificationResponseDto classification(@RequestBody @Valid ClassificationRequestDto request) {
        ClassificationResponseDto response = new ClassificationResponseDto();

        try {
            for (int i = 0; i < request.translatedText.size(); i++) {
                response.translatedText.add(request.translatedText.get(i));
                response.keywords.add(classificationService.classification(request.translatedText.get(i)));
            }
            log.info("Classification is {}", "success");
            response.success = true;
            response.message = "Success";

        } catch (Exception e) {
            e.printStackTrace();
            log.info("Classification is {}, Exception : {}", "fail", e.getMessage());
            response.success = false;
            response.message = e.getMessage();
            response.translatedText = null;
            response.keywords = null;
        }

        return response;
    }
}
