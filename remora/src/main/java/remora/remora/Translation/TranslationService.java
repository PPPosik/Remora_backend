package remora.remora.Translation;

import org.springframework.stereotype.Service;
import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

@Service
public class TranslationService {
    public Boolean checkSupportedLanguage(){
        /*
            To do : Check Language
         */
        return false;
    }

    public TranslationResponseDto translate(TranslationRequestDto request){
        /*
            To do : Translate
         */
        return new TranslationResponseDto();
    }
}
