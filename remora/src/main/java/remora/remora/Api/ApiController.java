package remora.remora.Api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Api.ApiDTO.DeleteRequestDto;
import remora.remora.Api.ApiDTO.SimpleResponseDto;
import remora.remora.Api.ApiDTO.UploadRequestDto;
import remora.remora.Api.ApiDTO.UploadResponseDto;

import java.io.IOException;
import java.util.List;

/**
 * Video 업로드 및 응답을 받기 위한 Controller
 */

@RestController
@RequestMapping("/remora")
public class ApiController {

    private ApiService apiService = new ApiService();

    @GetMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UploadResponseDto uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                  @RequestParam("needTranslate") String needTranslate) throws IOException {

        boolean trans = needTranslate.equals("true");

        UploadRequestDto uploadReqDto = new UploadRequestDto(files, trans);
        return apiService.uploadVideo(uploadReqDto);
    }

    @PutMapping("/video")
    SimpleResponseDto changeVideo(@RequestBody UploadRequestDto uploadReqDto){
        return apiService.changeVideo(uploadReqDto);
    }

    @DeleteMapping("/video")
    SimpleResponseDto deleteVideo(@RequestBody DeleteRequestDto deleteReqDto){
        return apiService.deleteVideo(deleteReqDto);
    }
}
