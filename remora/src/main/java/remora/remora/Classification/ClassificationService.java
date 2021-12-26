package remora.remora.Classification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;
import remora.remora.Classification.dto.ClassificationRequestDto;
import remora.remora.Classification.dto.ClassificationResponseDto;
import remora.remora.Common.Cli;

@Service
public class ClassificationService {
    public ClassificationResponseDto classification(ClassificationRequestDto request) {
        Dotenv dotenv = Dotenv.configure().load();
        ClassificationResponseDto response = new ClassificationResponseDto();
        response.success = false;
        response.keywords = new ArrayList<String>();

        final Boolean success = Cli.exec(dotenv.get("RUN_CLASSIFICATION"), "");
        if (success) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(dotenv.get("CLASSIFICATION_RESULT_PATH")));

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
