package remora.remora.MainControl;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import remora.remora.FrameExtraction.FrameExtractionController;
import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

import remora.remora.Translation.TranslationController;
import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

@RestController
public class MainController {
    @GetMapping("/test/extraction")
    public void testExtraction() {
        FrameExtractionController frameExtractionController = new FrameExtractionController();
        FrameExtractionRequestDto request = new FrameExtractionRequestDto();
        FrameExtractionResponseDto response = null;

        request.originVideo = new File("C:\\testVideo3.mp4");
        response = frameExtractionController.frameExtract(request);
        if (response != null && response.success) {
            System.out.println("Success frame extraction");
        }
    }

    @GetMapping("/test/translate")
    public void testTranslate() {
        TranslationController translationController = new TranslationController();
        TranslationRequestDto request = new TranslationRequestDto();
        TranslationResponseDto response = new TranslationResponseDto();

        request.language = "en";
        request.needTranslation = true;
        request.originText = "Java is a general-purpose, class-based, object-oriented programming language designed for having lesser implementation dependencies. It is a computing platform for application development. Java is fast, secure, and reliable, therefore. It is widely used for developing Java applications in laptops, data centers, game consoles, scientific supercomputers, cell phones, etc.";

        if (request.needTranslation) {
            response = translationController.translate(request);
        }

        if (response != null && response.success) {
            System.out.println("Success translation");
            System.out.println(response.translatedText);
        }
    }
}
