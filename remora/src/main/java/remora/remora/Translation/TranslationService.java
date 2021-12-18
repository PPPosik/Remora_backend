package remora.remora.Translation;

import org.springframework.stereotype.Service;

import remora.remora.Adapter.Papago;
import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class TranslationService {
    public TranslationResponseDto translate(TranslationRequestDto request) throws Exception {
        TranslationResponseDto response = new TranslationResponseDto();
        response.originText = request.originText;
        response.success = false;
        response.translatedText = null;

        if (!this.checkSupportedLanguage(request.language)) {
            throw new Exception("Cannot translate language " + request.language);
        }

        String responseBodyStr = Papago.call(request.originText);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseBodyStr);
        JSONObject jsonObj = (JSONObject) obj;
        // TODO find better solution for nested json
        response.translatedText = (String) ((JSONObject) ((JSONObject) jsonObj.get("message")).get("result"))
                .get("translatedText");
        response.success = true;

        return response;
    }

    private Boolean checkSupportedLanguage(String language) {
        // TODO only support en -> ko
        if (language.equals("en")) {
            return true;
        }
        else {
            return false;
        }
    }
}
