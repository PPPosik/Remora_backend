package remora.remora.Upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import remora.remora.Upload.dto.UploadRequestDto;
import remora.remora.Upload.dto.UploadResponseDto;

import java.io.IOException;

@RestController
public class UploadController {
    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    public UploadResponseDto uploadVideo(UploadRequestDto uploadReqDto){
        return uploadService.uploadVideo(uploadReqDto);
    }
}
