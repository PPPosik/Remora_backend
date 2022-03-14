package remora.remora.Classification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import remora.remora.Exception.NotSupportedLanguageException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ClassificationTest {
    @Value("${classification-result-path}")
    String classificationResultPath;

    @Autowired
    private ClassificationService classificationService;

    @Test
    public void classificationKoSuccess() {
        List<String> result = new ArrayList<>();
        List<String> expected = new ArrayList<>();

        try {
            result = classificationService.classification("안녕하세요");
        } catch (Exception e) {
            fail();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(classificationResultPath));
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                expected.add(str);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(result.size()).isEqualTo(expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    public void classificationEnSuccess() {
        List<String> result = new ArrayList<>();
        List<String> expected = new ArrayList<>();

        try {
            result = classificationService.classification("Hello");
        } catch (Exception e) {
            fail();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(classificationResultPath));
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
                expected.add(str);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(result.size()).isEqualTo(expected.size());

        for (int i = 0; i < expected.size(); i++) {
            assertThat(result.get(i)).isEqualTo(expected.get(i));
        }
    }

    @Test
    public void notSupportedLanguageExceptionTest() {
        assertThrows(NotSupportedLanguageException.class, () -> classificationService.classification("أهلا"));
        assertThrows(NotSupportedLanguageException.class, () -> classificationService.classification("Привет"));
        assertThrows(NotSupportedLanguageException.class, () -> classificationService.classification("Dia dhuit"));
    }

    @Test
    public void classificationModuleExceptionTest() {
    }

    @Test
    public void notExistKeywordExceptionTest() {
    }
}
