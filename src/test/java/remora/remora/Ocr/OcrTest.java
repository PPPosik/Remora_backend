package remora.remora.Ocr;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class OcrTest {
    @Value("${frame-path}")
    String framePath;
    @Value("${ocr-module-path}")
    String ocrModulePath;
    @Value("${ocr-result-path}")
    String ocrResultPath;

    @Autowired
    OcrService ocrService;

    private ArrayList<Integer> frameSet;
    private String videoCode;

    @BeforeEach
    public void init() {
        frameSet = new ArrayList<>();
        videoCode = "1";

        frameSet.add(0);
        frameSet.add(150);
        frameSet.add(300);
        frameSet.add(450);
        frameSet.add(600);
        frameSet.add(750);
        frameSet.add(900);
        frameSet.add(1050);
        frameSet.add(1200);
        frameSet.add(1350);
        frameSet.add(1500);
        frameSet.add(1650);
        frameSet.add(1800);
        frameSet.add(1950);
        frameSet.add(2100);
        frameSet.add(2250);
        frameSet.add(2400);
        frameSet.add(2550);
        frameSet.add(2700);
        frameSet.add(2850);
        frameSet.add(3000);
        frameSet.add(3150);
        frameSet.add(3300);
        frameSet.add(3450);
        frameSet.add(3600);
        frameSet.add(3750);
    }

    @AfterEach
    public void resetEnv() {
        ocrService.ocrModulePath = this.ocrModulePath;
        ocrService.ocrResultPath = this.ocrResultPath;
    }

    @Test
    public void ocrSuccess() throws Exception {
            String result = ocrService.detection(frameSet, videoCode);

            StringBuilder expectedStr = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(ocrResultPath));
            String str;

            while ((str = reader.readLine()) != null) {
                expectedStr.append(str + "\n");
            }
            reader.close();

            assertThat(result).isEqualTo(expectedStr.toString());
    }

    @Test
    public void ocrFail() {
        ocrService.ocrModulePath = null;
        assertThrows(Exception.class, () -> ocrService.detection(frameSet, videoCode));
    }

    @Test
    public void noText() {
        ocrService.ocrResultPath = framePath + ".gitkeep";
        assertThrows(Exception.class, () -> ocrService.detection(frameSet, videoCode));
    }
}
