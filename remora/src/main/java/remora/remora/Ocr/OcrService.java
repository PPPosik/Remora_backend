package remora.remora.Ocr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.Common.Cli;

@Service
public class OcrService {
    @Value("${ocr-module-path}")
    String ocrModulePath;
    @Value("${ocr-result-path}")
    String ocrResultPath;

    public String detection(ArrayList<Integer> frameSet, String videoCode) throws Exception {
        StringBuilder args = new StringBuilder();
        StringBuilder ret = new StringBuilder();

        for (Integer n : frameSet) {
            args.append(videoCode + "_" + n + ".png ");
        }
        System.out.println("args = " + args);

        if (Cli.exec(ocrModulePath, args.toString())) {
            BufferedReader reader = new BufferedReader(new FileReader(ocrResultPath));

            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                ret.append(str + "\n");
            }
            reader.close();
        } else {
            System.out.println("Run OCR module error");
            throw new Exception("Run OCR module error");
        }

        if (ret.length() == 0) {
            System.out.println("There is no text in video");
            throw new Exception("There is no text in video");
        }

        return ret.toString();
    }
}
