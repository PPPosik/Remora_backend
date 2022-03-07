package remora.remora.Classification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.Common.Cli;

@Service
public class ClassificationService {
    @Value("${classification-module-path}")
    String classificationModulePath;
    @Value("${classification-result-path}")
    String classificationResultPath;
    @Value("${classification-input-path}")
    String classificationInputPath;

    public List<String> classification(String translatedText) throws Exception {
        List<String> keywords = new ArrayList<>();

        writeOriginText(translatedText);

        if (Cli.exec(classificationModulePath, classificationInputPath)) {
            BufferedReader reader = new BufferedReader(new FileReader(classificationResultPath));

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                keywords.add(str);
            }
            reader.close();
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
            throw new IOException("writeOriginText fail : " + e);
        }
    }
}
