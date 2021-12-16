package remora.remora.Translation;

import org.springframework.web.bind.annotation.RestController;

import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

@RestController
public class TranslationController {
    TranslationService translationService = new TranslationService();

    public TranslationResponseDto translate(TranslationRequestDto request) {
        try {
            return translationService.translate(request);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
