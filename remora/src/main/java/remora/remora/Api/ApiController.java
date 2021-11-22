package remora.remora.Api;

import org.springframework.web.bind.annotation.*;
import remora.remora.Api.ApiDTO.DeleteRequestDto;
import remora.remora.Api.ApiDTO.SimpleResponseDto;
import remora.remora.Api.ApiDTO.UploadRequestDto;
import remora.remora.Api.ApiDTO.UploadResponseDto;

/**
 * Video 업로드 및 응답을 받기 위한 Controller
 */

@RestController
@RequestMapping("/remora")
public class ApiController {

    private ApiService apiService;

    @GetMapping("/video")
    UploadResponseDto uploadVideo(@RequestBody UploadRequestDto uploadReqDto){
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
