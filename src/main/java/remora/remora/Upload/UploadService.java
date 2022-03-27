package remora.remora.Upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Upload.dto.UploadRequestDto;
import remora.remora.Upload.dto.UploadResponseDto;
import remora.remora.Exception.InvalidVideoException;

import java.io.File;

@Service
public class UploadService {
    @Value("${video-path}")
    String videoPath;

    private static int fileNumber = 0;
    private Logger log = LoggerFactory.getLogger(getClass());

    UploadResponseDto uploadVideo(UploadRequestDto request) {
        UploadResponseDto response = new UploadResponseDto();

        MultipartFile file = request.getVideoFile();
        String fileType = file.getContentType();

        try {
            if (fileType.contains("video") || fileType.contains("Video")) {
                File dest = new File(videoPath + "req_video" + fileNumber);
                file.transferTo(dest);

                log.info("Request file = {}", file.getOriginalFilename());
                log.info("Upload request is {}", "success");
                response.success = true;
                response.videoCode = Integer.toString(++fileNumber);
                response.message = "Success";
                response.needTranslation = request.getNeedTranslate();
                log.info("Video code = {}, Need Translation = {}", response.videoCode, response.needTranslation);
            }

            else throw new InvalidVideoException();

        } catch (Exception e) {
            e.printStackTrace();

            log.debug("Request file = {}", file.getOriginalFilename());
            log.debug("Upload request is {}", "fail");
            response.success = false;
            response.videoCode = "-1";
            response.message = e.getMessage();
            response.needTranslation = request.getNeedTranslate();
            log.debug("Video code = {}, Need Translation = {}", response.videoCode, response.needTranslation);
        }

        return response;
    }
}
