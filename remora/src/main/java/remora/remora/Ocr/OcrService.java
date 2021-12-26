package remora.remora.Ocr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import io.github.cdimascio.dotenv.Dotenv;
import remora.remora.Common.Cli;
import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

@Service
public class OcrService {
    public OcrResponseDto detection(OcrRequestDto request) {
        Dotenv dotenv = Dotenv.configure().load();
        OcrResponseDto response = new OcrResponseDto();
        response.success = false;
        response.originResultText = new StringBuffer();

        final Boolean success = Cli.exec(dotenv.get("RUN_OCR"), "");
        if (success) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(dotenv.get("OCR_RESULT_PATH")));

                String str;
                while ((str = reader.readLine()) != null) {
                    System.out.println(str);
                    response.originResultText.append(str + "\n");
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
