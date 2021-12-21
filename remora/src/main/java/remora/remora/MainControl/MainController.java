package remora.remora.MainControl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


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
    ArrayList<UploadResponseDto> uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                             @RequestParam("needTranslate") List<String> needTranslate) throws IOException {

        ArrayList<UploadResponseDto> uploadResDtos = new ArrayList<UploadResponseDto>();

        for(int i = 0; i < needTranslate.size(); i++) {
            UploadRequestDto uploadReqDto = new UploadRequestDto(files.get(i), needTranslate.get(i).equals("true"));
            uploadResDtos.add(apiController.uploadVideo(uploadReqDto));
        }

        for(UploadResponseDto uploadResDto : uploadResDtos){
            FrameExtractionRequestDto frameExReqDto = new FrameExtractionRequestDto();
            frameExReqDto.originVideo = new File(System.getenv("VIDEOPATH") + "req_video" + uploadResDto.code);
            FrameExtractionResponseDto frameExResDto = extraction(frameExReqDto);
            System.out.println(uploadResDto.code + " : " + frameExResDto.success);

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

        return uploadResDtos;
    }

    @PutMapping("/upload")
    SimpleResponseDto changeVideo(@RequestBody UploadRequestDto uploadReqDto){
        return apiController.changeVideo(uploadReqDto);
    }

    @DeleteMapping("/upload")
    SimpleResponseDto deleteVideo(@RequestBody DeleteRequestDto deleteReqDto){
        return apiController.deleteVideo(deleteReqDto);
    }

    public FrameExtractionResponseDto extraction(FrameExtractionRequestDto frameExReqDto) {
        return frameExtractionController.frameExtract(frameExReqDto);
    }
    
    /*
        To do : 번역 모듈을 호출할 때 참고할 코드임.
     */
    @GetMapping("/test/translate")
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
}
