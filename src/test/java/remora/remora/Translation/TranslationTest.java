package remora.remora.Translation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TranslationTest {
    @Autowired
    private TranslationService translationService;

    @Test
    public void translateKo2Ko() {
        try {
            String result = translationService.translate("안녕하세요", true);
            assertThat(result).isEqualTo("안녕하세요");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void notTranslateKo2Ko() {
        try {
            String result = translationService.translate("안녕하세요", false);
            assertThat(result).isEqualTo("안녕하세요");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void translateEn2Ko() {
        try {
            String result = translationService.translate("Hello", true);
            assertThat(result).isEqualTo("안녕");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void notTranslateEn2Ko() {
        try {
            String result = translationService.translate("Hello", false);
            assertThat(result).isEqualTo("Hello");
        } catch (Exception e) {
            fail();
        }
    }
}
