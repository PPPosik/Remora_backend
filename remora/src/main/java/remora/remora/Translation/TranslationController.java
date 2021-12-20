package remora.remora.Translation;

import org.springframework.web.bind.annotation.RestController;
import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

@RestController
public class TranslationController {
    private TranslationService translationService = new TranslationService();

    public TranslationResponseDto translate(TranslationRequestDto request){
          /*
            To do : Translate Service Call
         */
        return translationService.translate(request);
    }
}
