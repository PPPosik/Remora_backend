package remora.remora.Upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import remora.remora.Upload.dto.UploadRequestDto;
import remora.remora.Upload.dto.UploadResponseDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UploadController {
    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService){
        this.uploadService = uploadService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResponseDto> uploadVideo(@RequestParam("originVideo") List<MultipartFile> files,
                                         @RequestParam("needTranslate") List<String> needTranslate){

        List<UploadResponseDto> response = new ArrayList<>();
        for(int i = 0; i < files.size(); i++){
            UploadRequestDto request = new UploadRequestDto(files.get(i),
                                                            needTranslate.get(i).equals("true"));
            response.add(uploadService.uploadVideo(request));
        }

        return response;
    }
}
