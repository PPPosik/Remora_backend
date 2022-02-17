package remora.remora.Upload;

import org.springframework.web.bind.annotation.*;
import remora.remora.Upload.dto.UploadRequestDto;
import remora.remora.Upload.dto.UploadResponseDto;

import java.io.IOException;

@RestController
public class UploadController {

    private UploadService uploadService = new UploadService();

    public UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto) throws IOException {
        return uploadService.uploadVideo(uploadReqDto);
    }
}
