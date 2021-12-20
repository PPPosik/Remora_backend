package remora.remora.Api;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Api.dto.DeleteRequestDto;
import remora.remora.Api.dto.SimpleResponseDto;
import remora.remora.Api.dto.UploadRequestDto;
import remora.remora.Api.dto.UploadResponseDto;

import java.io.File;
import java.io.IOException;

@Service
public class ApiService {
    UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto) throws IOException {
        UploadResponseDto uploadResDto = new UploadResponseDto();
        int fileNumber = 1;

        for(MultipartFile file : uploadReqDto.getVideoFiles()){
            String fileType = file.getContentType();

            if(fileType.contains("video") || fileType.contains("Video")){
                File dest = new File(System.getenv("VIDEOPATH") + "/" + "req_video" + fileNumber++);
                file.transferTo(dest);
            }
        }

        /*
            To Do : Set UploadResponseDto!
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
