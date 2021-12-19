package remora.remora.Api;

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
public class ApiController {

    private ApiService apiService = new ApiService();

    /**
     *
     * @param files
     * @param needTranslate
     * @return UploadResponseDto
     * @throws IOException
     */
    public UploadResponseDto uploadVideo(List<MultipartFile> files,
                                         String needTranslate) throws IOException {

        Boolean trans = needTranslate.equals("true");

        UploadRequestDto uploadReqDto = new UploadRequestDto(files, trans);
        return apiService.uploadVideo(uploadReqDto);
    }

    /**
     *
     * @param uploadReqDto
     * @return SimpleResponseDto
     */
    public SimpleResponseDto changeVideo(UploadRequestDto uploadReqDto){
        return apiService.changeVideo(uploadReqDto);
    }

    /**
     *
     * @param deleteReqDto
     * @return SimpleResponseDto
     */
    public SimpleResponseDto deleteVideo(DeleteRequestDto deleteReqDto){
        return apiService.deleteVideo(deleteReqDto);
    }
}
