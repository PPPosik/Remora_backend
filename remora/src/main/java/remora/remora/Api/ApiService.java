package remora.remora.Api;

import io.github.cdimascio.dotenv.Dotenv;
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
    static int fileNumber = 1;

    UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto) throws IOException {
        Dotenv dotenv = Dotenv.configure().load();
        UploadResponseDto uploadResDto = new UploadResponseDto();

        MultipartFile file = uploadReqDto.getVideoFile();
        String fileType = file.getContentType();

        if(fileType.contains("video") || fileType.contains("Video")){
            File dest = new File(dotenv.get("VIDEO_PATH") + "req_video" + fileNumber);
            file.transferTo(dest);
        }

        uploadResDto.code = fileNumber++;
        uploadResDto.needTranslation = uploadReqDto.getNeedTranslate();

        return uploadResDto;
    }

    SimpleResponseDto changeVideo(UploadRequestDto uploadReqDto) throws IOException {
        SimpleResponseDto simpleResDto = new SimpleResponseDto();
        return simpleResDto;
    }

    SimpleResponseDto deleteVideo(DeleteRequestDto deleteReqDto){
        SimpleResponseDto simpleResDto = new SimpleResponseDto();
        return simpleResDto;
    }

}
