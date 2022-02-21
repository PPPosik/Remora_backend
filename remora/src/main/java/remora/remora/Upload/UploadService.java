package remora.remora.Upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Upload.dto.UploadRequestDto;
import remora.remora.Upload.dto.UploadResponseDto;

import java.io.File;

@Service
public class UploadService {
    @Value("${video-path}")
    String videoPath;

    private static int fileNumber = 1;

    UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto) {
        UploadResponseDto uploadResDto = new UploadResponseDto();

        MultipartFile file = uploadReqDto.getVideoFile();
        String fileType = file.getContentType();
        /*
            ToDo : 프레임 추출 API 호출을 위한 값을 반환해야 함.
         */
        try {
            if (fileType.contains("video") || fileType.contains("Video")) {
                File dest = new File(videoPath + "req_video" + fileNumber);
                file.transferTo(dest);

                uploadResDto.setSuccess(true);
                uploadResDto.setMessage("Success");
                uploadResDto.setNeedTranslation(uploadReqDto.getNeedTranslate());
                uploadResDto.setCode(fileNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();

            uploadResDto.setMessage(e.getMessage());
            uploadResDto.setNeedTranslation(uploadReqDto.getNeedTranslate());
            uploadResDto.setSuccess(false);
            uploadResDto.setCode(-1);
        }

        return uploadResDto;
    }
}
