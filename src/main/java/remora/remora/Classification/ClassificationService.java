package remora.remora.Classification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.Classification.Adapter.LanguageDetectionAdapter;
import remora.remora.Common.Cli;
import remora.remora.Classification.Enum.DetectionLanguageCode;
import remora.remora.Exception.ClassificationModuleException;
import remora.remora.Exception.NotExistKeywordException;
import remora.remora.Exception.NotSupportedLanguageException;

@Service
public class ClassificationService {
    private final LanguageDetectionAdapter languageDetectionModel;
    private Logger log = LoggerFactory.getLogger(getClass());

    @Value("${classification-module-path}")
    String classificationModulePath;
    @Value("${classification-result-path}")
    String classificationResultPath;
    @Value("${classification-input-path}")
    String classificationInputPath;

    @Autowired
    public ClassificationService(LanguageDetectionAdapter languageDetectionModel) {
        this.languageDetectionModel = languageDetectionModel;
    }

    public List<String> classification(String translatedText) throws Exception {
        List<String> keywords = new ArrayList<>();

        DetectionLanguageCode language = languageDetectionModel.detectLanguage(translatedText);
        if (language != DetectionLanguageCode.KO && language != DetectionLanguageCode.EN) {
            log.error("Classification Fail, Language is not supported");
            throw new NotSupportedLanguageException("Language(" + language.toString() + ") is not supported");
        }

        writeOriginText(translatedText);

        if (Cli.exec(classificationModulePath, classificationInputPath)) {
            BufferedReader reader = new BufferedReader(new FileReader(classificationResultPath));

            String str;
            while ((str = reader.readLine()) != null) {
                log.info("keyword : {}", str);
                keywords.add(str);
            }
            reader.close();
        } else {
            throw new ClassificationModuleException();
        }

        if (keywords.size() == 0) {
            throw new NotExistKeywordException();
        }

        return keywords;
    }

    private void writeOriginText(String str) throws IOException {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(classificationInputPath));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            log.error("WriteOriginText Fail, {}", e.getMessage());
            throw new IOException("writeOriginText fail : " + e);
        }
    }
}
