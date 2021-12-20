package remora.remora.Api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Api.dto.DeleteRequestDto;
import remora.remora.Api.dto.SimpleResponseDto;
import remora.remora.Api.dto.UploadRequestDto;
import remora.remora.Api.dto.UploadResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * Video 업로드 및 응답을 받기 위한 Controller
 */

@RestController
@RequestMapping("/")
public class ApiController {

    private ApiService apiService = new ApiService();

    @GetMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadResponseDto uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                  @RequestParam("needTranslate") String needTranslate) throws IOException {

        Boolean trans = needTranslate.equals("true");

        UploadRequestDto uploadReqDto = new UploadRequestDto(files, trans);
        return apiService.uploadVideo(uploadReqDto);
    }

    @PutMapping("/upload")
    SimpleResponseDto changeVideo(@RequestBody UploadRequestDto uploadReqDto){
        return apiService.changeVideo(uploadReqDto);
    }

    @DeleteMapping("/upload")
    SimpleResponseDto deleteVideo(@RequestBody DeleteRequestDto deleteReqDto){
        return apiService.deleteVideo(deleteReqDto);
    }
}
