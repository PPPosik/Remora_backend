package remora.remora.Translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import remora.remora.Translation.Adapter.TranslationAdapter;

@Service
public class TranslationService {
    private final TranslationAdapter translationModel;

    @Autowired
    public TranslationService(TranslationAdapter translationModel) {
        this.translationModel = translationModel;
    }

    public String translate(String text, Boolean needTranslation) throws Exception {
        if (!needTranslation) {
            return text;
        }

        String responseBodyStr = translationModel.translate(text);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseBodyStr);
        JSONObject jsonObj = (JSONObject) obj;
        return (String) ((JSONObject) ((JSONObject) jsonObj.get("message")).get("result")).get("translatedText");
    }
}
