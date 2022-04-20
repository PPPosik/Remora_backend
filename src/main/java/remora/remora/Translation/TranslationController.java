package remora.remora.Translation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import remora.remora.Exception.RequestDataLengthDifferentException;
import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

import javax.validation.Valid;

@RestController
public class TranslationController {
    private final TranslationService translationService;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping(value = "/text/translated", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public TranslationResponseDto translate(@RequestBody @Valid TranslationRequestDto request) {
        TranslationResponseDto response = new TranslationResponseDto();

        try {
            if (request.text.size() != request.needTranslation.size()) {
                log.debug("Translated is {}", "fail");
                throw new RequestDataLengthDifferentException("Translated fail, text length : " + request.text.size() + ", needTranslation length : " + request.needTranslation.size());
            }

            for (int i = 0; i < request.text.size(); i++) {
                response.translatedText.add(translationService.translate(request.text.get(i), request.needTranslation.get(i)));
                response.text.add(request.text.get(i));
            }
            log.info("Translated is {}", "success");
            response.success = true;
            response.message = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Translated is {}", "fail");
            response.success = false;
            response.message = e.toString();
            response.text = null;
            response.translatedText = null;
        }

        return response;
    }
}
