package remora.remora.Translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return translationModel.translate(text);
    }
}
