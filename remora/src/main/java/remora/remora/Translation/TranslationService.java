package remora.remora.Translation;

import org.springframework.stereotype.Service;

import remora.remora.Adapter.Papago;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Service
public class TranslationService {
    public String translate(String text, Boolean needTranslation) throws Exception {
        if (!needTranslation) {
            return text;
        }

        String responseBodyStr = Papago.translate(text);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseBodyStr);
        JSONObject jsonObj = (JSONObject) obj;
        return (String) ((JSONObject) ((JSONObject) jsonObj.get("message")).get("result")).get("translatedText");
    }
}
