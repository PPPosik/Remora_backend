package remora.remora.Ocr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.Common.Cli;
import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;

@Service
public class OcrService {
    @Value("${ocr-module-path}")
    String ocrModulePath;
    @Value("${ocr-result-path}")
    String ocrResultPath;

    public OcrResponseDto detection(OcrRequestDto request) {
        OcrResponseDto response = new OcrResponseDto();
        response.success = false;
        response.originResultText = new StringBuffer();

        final Boolean success = Cli.exec(ocrModulePath, "");
        if (success) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(ocrResultPath));

                String str;
                while ((str = reader.readLine()) != null) {
                    System.out.println(str);
                    response.originResultText.append(str + "\n");
                }
                reader.close();
                response.success = true;
            } catch (IOException e) {
                e.printStackTrace();
                return response;
            }
        }

        return response;
    }
}
