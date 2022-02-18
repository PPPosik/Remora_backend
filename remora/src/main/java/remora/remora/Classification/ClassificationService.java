package remora.remora.Classification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.Classification.dto.ClassificationRequestDto;
import remora.remora.Classification.dto.ClassificationResponseDto;
import remora.remora.Common.Cli;

@Service
public class ClassificationService {
    @Value("${classification-module-path}")
    String classificationModulePath;
    @Value("${classification-result-path}")
    String classificationResultPath;

    public ClassificationResponseDto classification(ClassificationRequestDto request) {
        ClassificationResponseDto response = new ClassificationResponseDto();
        response.success = false;
        response.keywords = new ArrayList<String>();

        final Boolean success = Cli.exec(classificationModulePath, "");
        if (success) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(classificationResultPath));

                String str;
                while ((str = reader.readLine()) != null) {
                    System.out.println(str);
                    response.keywords.add(str);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                return response;
            }
        }

        return response;
    }
}
