package remora.remora.Translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

import javax.validation.Valid;

@RestController
public class TranslationController {
    private final TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/text/translated")
    @ResponseBody
    public TranslationResponseDto translate(@RequestBody @Valid TranslationRequestDto request) {
        TranslationResponseDto response = new TranslationResponseDto();

        try {
            for (int i = 0; i < request.text.size(); i++) {
                response.translatedText.add(translationService.translate(request.text.get(i), request.needTranslation.get(i)));
                response.text.add(request.text.get(i));
            }

            response.success = true;
            response.message = "Success";
        } catch (Exception e) {
            e.printStackTrace();

            response.success = false;
            response.message = e.toString();
            response.text = null;
            response.translatedText = null;
        }

        return response;
    }
}
