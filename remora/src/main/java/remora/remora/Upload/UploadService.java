package remora.remora.Upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger log = LoggerFactory.getLogger(getClass());

    UploadResponseDto uploadVideo(UploadRequestDto request) {
        UploadResponseDto response = new UploadResponseDto();

        MultipartFile file = request.getVideoFile();
        String fileType = file.getContentType();

        try {
            if (fileType.contains("video") || fileType.contains("Video")) {
                File dest = new File(videoPath + "req_video" + fileNumber);
                file.transferTo(dest);

                log.info("Request video = {}", file.getOriginalFilename());
                log.info("Upload request is {}", "success");
                response.success = true;
                response.videoCode = fileNumber++;
                response.message = "Success";
                response.needTranslation = request.getNeedTranslate();
                log.info("Video code = {}, Need Translation = {}", response.videoCode, response.needTranslation);
            }
        } catch (Exception e) {
            e.printStackTrace();

            log.info("Request video = {}", file.getOriginalFilename());
            log.info("Upload request is {}", "fail");
            response.success = false;
            response.videoCode = -1;
            response.message = "Fail";
            response.needTranslation = request.getNeedTranslate();
            log.info("Video code = {}, Need Translation = {}", response.videoCode, response.needTranslation);
        }

        return response;
    }
}
