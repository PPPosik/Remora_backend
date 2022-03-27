package remora.remora.Classification.Adapter;

import remora.remora.Classification.Enum.DetectionLanguageCode;

public interface LanguageDetectionAdapter {
    DetectionLanguageCode detectLanguage(String str) throws Exception;
}
