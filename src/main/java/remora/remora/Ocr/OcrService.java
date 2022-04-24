package remora.remora.Ocr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.Common.Cli;
import remora.remora.Exception.NotExistTextException;
import remora.remora.Exception.OcrModuleException;

@Service
public class OcrService {
    @Value("${ocr-module-path}")
    String ocrModulePath;
    @Value("${ocr-result-path}")
    String ocrResultPath;

    private Logger log = LoggerFactory.getLogger(getClass());

    public String detection(ArrayList<Integer> frameSet, String videoCode) throws Exception {
        StringBuilder args = new StringBuilder();
        StringBuilder ret = new StringBuilder();

        for (Integer n : frameSet) {
            args.append(videoCode + "_" + n + ".png ");
        }

        log.info("args = {}", args);

        if (Cli.exec(ocrModulePath, args.toString())) {
            BufferedReader reader = new BufferedReader(new FileReader(ocrResultPath));

            String str;
            while ((str = reader.readLine()) != null) {
                ret.append(str + "\n");
            }
            reader.close();
        } else {
            log.error("OCR Fail, OCR module error");
            throw new OcrModuleException();
        }

        if (ret.length() == 0) {
            log.error("OCR Fail, There is no text in video");
            throw new NotExistTextException();
        }

        return ret.toString();
    }
}
