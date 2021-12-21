package remora.remora.Api.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 동영상을 업로드할 때 필요한 Object
 */
public class UploadRequestDto {
    /**
     * 원본 동영상 파일
     */
    private MultipartFile originVideo;
    /**
     * 번역 희망 여부
     */
    private Boolean needTranslate;

    public UploadRequestDto(MultipartFile file, Boolean needTranslate) {
        this.originVideo = file;
        this.needTranslate = needTranslate;
    }

    public Boolean getNeedTranslate(){
        return this.needTranslate;
    }

    public MultipartFile getVideoFile(){
        return this.originVideo;
    }
}
