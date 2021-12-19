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
import remora.remora.Classification.ClassificationController;
import remora.remora.FrameExtraction.FrameExtractionController;
import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;
import remora.remora.Ocr.OcrService;
import remora.remora.Translation.TranslationController;

@RestController
public class MainController {
    FrameExtractionController frameExtractionController = new FrameExtractionController();
    ApiController apiController = new ApiController();
    ClassificationController classificationController = new ClassificationController();
    TranslationController translationController = new TranslationController();
    OcrService ocrService = new OcrService();

    @GetMapping("/test/extraction")
    public void testExtraction() {
        //FrameExtractinController frameExtractionController = new FrameExtractinController();
        FrameExtractionRequestDto request = new FrameExtractionRequestDto();
        FrameExtractionResponseDto response = null;

        request.originVideo = new File("C:\\testVideo3.mp4");
        response = frameExtractionController.frameExtract(request);
        if(response.success) {
            System.out.println("Success frame extraction");
        }
    }

    @GetMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadResponseDto uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                  @RequestParam("needTranslate") String needTranslate) throws IOException {
        return apiController.uploadVideo(files, needTranslate);
    }

    @PutMapping("/upload")
    SimpleResponseDto changeVideo(@RequestBody UploadRequestDto uploadReqDto) {
        return apiController.changeVideo(uploadReqDto);
    }

    @DeleteMapping("/upload")
    SimpleResponseDto deleteVideo(@RequestBody DeleteRequestDto deleteReqDto){
        return apiController.deleteVideo(deleteReqDto);
    }
}
