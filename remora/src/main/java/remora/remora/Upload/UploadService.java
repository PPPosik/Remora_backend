package remora.remora.Upload;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Upload.dto.UploadRequestDto;
import remora.remora.Upload.dto.UploadResponseDto;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {
    static int fileNumber = 1;

    UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto){
        Dotenv dotenv = Dotenv.configure().load();
        UploadResponseDto uploadResDto = new UploadResponseDto();

        MultipartFile file = uploadReqDto.getVideoFile();
        String fileType = file.getContentType();
        /*
            ToDo : 프레임 추출 API 호출을 위한 값을 반환해야 함.
         */
        try {
            if (fileType.contains("video") || fileType.contains("Video")) {
                File dest = new File(dotenv.get("VIDEO_PATH") + "req_video" + fileNumber);
                file.transferTo(dest);

                uploadResDto.setSuccess(true);
                uploadResDto.setMessage("Success");
                uploadResDto.setNeedTranslation(uploadReqDto.getNeedTranslate());
                uploadResDto.setCode(fileNumber);
            }
        } catch (Exception e){
            e.printStackTrace();

            uploadResDto.setMessage(e.getMessage());
            uploadResDto.setNeedTranslation(uploadReqDto.getNeedTranslate());
            uploadResDto.setSuccess(false);
            uploadResDto.setCode(-1);
        }

        return uploadResDto;
    }
}g
