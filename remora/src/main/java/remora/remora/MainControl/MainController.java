package remora.remora.MainControl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import remora.remora.Api.ApiController;
import remora.remora.Api.dto.UploadRequestDto;
import remora.remora.Api.dto.UploadResponseDto;
import remora.remora.Classification.ClassificationController;
import remora.remora.Classification.dto.ClassificationRequestDto;
import remora.remora.Classification.dto.ClassificationResponseDto;
import remora.remora.FrameExtraction.FrameExtractionController;
import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;
import remora.remora.Ocr.OcrController;
import remora.remora.Ocr.dto.OcrRequestDto;
import remora.remora.Ocr.dto.OcrResponseDto;
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
    @ApiOperation(value = "Main Controller Swagger", produces = "multipart/form-data")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ArrayList<UploadResponseDto> uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                             @Parameter(description = "번역 희망 여부(true or false)", required = true)
                                             @RequestParam("needTranslate") List<String> needTranslate) throws IOException {
        Dotenv dotenv = Dotenv.configure().load();
        ArrayList<UploadResponseDto> uploadResDtos = new ArrayList<UploadResponseDto>();

        for(int i = 0; i < needTranslate.size(); i++) {
            UploadRequestDto uploadReqDto = new UploadRequestDto(files.get(i), needTranslate.get(i).equals("true"));
            uploadResDtos.add(apiController.uploadVideo(uploadReqDto));
        }

        for(UploadResponseDto uploadResDto : uploadResDtos){
            FrameExtractionRequestDto frameExReqDto = new FrameExtractionRequestDto();
            frameExReqDto.originVideo = new File(dotenv.get("VIDEO_PATH") + "req_video" + uploadResDto.code);
            FrameExtractionResponseDto frameExResDto = extraction(frameExReqDto);


            /*
                To do : Ocr Service Call
             */

            /*
                To do : Classification Service Call
             */

            /*
                To do : Translate Service Call
             */
        }

        File path = new File(dotenv.get("VIDEO_PATH"));
        File[] folderList = path.listFiles();

        for (File file : folderList) {
            file.delete();
        }

        path = new File(dotenv.get("FRAME_PATH"));
        folderList = path.listFiles();

        for (File file : folderList) {
            file.delete();
        }

        return uploadResDtos;
    }

    public FrameExtractionResponseDto extraction(FrameExtractionRequestDto frameExReqDto) {
        return frameExtractionController.frameExtract(frameExReqDto);
    }
    
    /*
        To do : 번역 모듈 호출
     */
    public void translate() {
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

    @GetMapping("/test/ocr")
    public void testOcr() {
        OcrController ocrController = new OcrController();
        OcrRequestDto request = new OcrRequestDto();
        request.frameSet = null;

        OcrResponseDto response = ocrController.detection(request);
        System.out.println("Ocr Response : " + response.originResultText);
    }

    @GetMapping("/test/classification")
    public void testClassification() {
        ClassificationController classificationController = new ClassificationController();
        ClassificationRequestDto request = new ClassificationRequestDto();
        request.language = "en";
        request.originResultText = "";
        request.translatedResultText = "";

        ClassificationResponseDto response = classificationController.classification(request);
        System.out.println("Classification Response : ");
        for (String keyword : response.keywords) {
            System.out.println(keyword);
        }
    }
}
