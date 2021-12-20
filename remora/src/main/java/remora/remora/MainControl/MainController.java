package remora.remora.MainControl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import remora.remora.Api.ApiController;
import remora.remora.Api.dto.DeleteRequestDto;
import remora.remora.Api.dto.SimpleResponseDto;
import remora.remora.Api.dto.UploadRequestDto;
import remora.remora.Api.dto.UploadResponseDto;
import remora.remora.FrameExtraction.FrameExtractionController;
import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

import remora.remora.Translation.TranslationController;
import remora.remora.Translation.dto.TranslationRequestDto;
import remora.remora.Translation.dto.TranslationResponseDto;

@RestController
public class MainController {
    FrameExtractionController frameExtractionController = new FrameExtractionController();
    TranslationController translationController = new TranslationController();
    ApiController apiController = new ApiController();
    
    /*
        To do : 다음과 같은 OcrController 객체가 생성 되어야 함.
         
        OcrController ocrController = new OcrController();
     */
    
    @GetMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadResponseDto uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                  @RequestParam("needTranslate") String needTranslate) throws IOException {

        Boolean trans = needTranslate.equals("true");
        UploadRequestDto request = new UploadRequestDto(files, trans);

        return apiController.uploadVideo(request);
    }

    @PutMapping("/upload")
    SimpleResponseDto changeVideo(@RequestBody UploadRequestDto uploadReqDto){
        return apiController.changeVideo(uploadReqDto);
    }

    @DeleteMapping("/upload")
    SimpleResponseDto deleteVideo(@RequestBody DeleteRequestDto deleteReqDto){
        return apiController.deleteVideo(deleteReqDto);
    }
    
    /*
        To do : 프레임 추출 모듈을 호출할 때 참고할 코드이며, File path는 환경변수로 관리할 예정임.
     */
    @GetMapping("/test/extraction")
    public void testExtraction() {
        FrameExtractionRequestDto request = new FrameExtractionRequestDto();
        FrameExtractionResponseDto response = null;

        request.originVideo = new File("C:\\testVideo3.mp4");
        response = frameExtractionController.frameExtract(request);
        if (response != null && response.success) {
            System.out.println("Success frame extraction");
        }
    }
    
    /*
        To do : 번역 모듈을 호출할 때 참고할 코드임.
     */
    @GetMapping("/test/translate")
    public void testTranslate() {
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
