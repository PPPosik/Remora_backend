package remora.remora.Api.ApiDTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 동영상을 업로드할 때 필요한 Object
 */
public class UploadRequestDto {
    /**
     * 원본 동영상 파일
     */
    private List<MultipartFile> originVideo;
    /**
     * 번역 희망 여부
     */
    private Boolean needTranslate;

    public UploadRequestDto(List<MultipartFile> files, Boolean needTranslate) {
        originVideo = files;
        this.needTranslate = needTranslate;
    }

    public Boolean getNeedTranslate(){
        return this.needTranslate;
    }

    public List<MultipartFile> getVideoFiles(){
        return this.originVideo;
    }
}
