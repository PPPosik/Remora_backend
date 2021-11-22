package remora.remora.Api;

import org.springframework.stereotype.Service;
import remora.remora.Api.ApiDTO.DeleteRequestDto;
import remora.remora.Api.ApiDTO.SimpleResponseDto;
import remora.remora.Api.ApiDTO.UploadRequestDto;
import remora.remora.Api.ApiDTO.UploadResponseDto;

@Service
public class ApiService {
    UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto){
        UploadResponseDto uploadResDto = new UploadResponseDto();
        /*
            To do
         */
        return uploadResDto;
    }

    SimpleResponseDto changeVideo(UploadRequestDto uploadReqDto){
        SimpleResponseDto simpleResDto = new SimpleResponseDto();
        /*
            To do
         */
        return simpleResDto;
    }

    SimpleResponseDto deleteVideo(DeleteRequestDto deleteReqDto){
        SimpleResponseDto simpleResDto = new SimpleResponseDto();
        /*
            To do
         */
        return simpleResDto;
    }

}
