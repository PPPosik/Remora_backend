package remora.remora.Upload.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadRequestDto {
    private MultipartFile originVideo;
    private boolean needTranslate;

    public UploadRequestDto(MultipartFile file, Boolean needTranslate) {
        this.originVideo = file;
        this.needTranslate = needTranslate;
    }

    public boolean getNeedTranslate(){
        return this.needTranslate;
    }

    public MultipartFile getVideoFile(){
        return this.originVideo;
    }
}
