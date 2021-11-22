package remora.remora.Api.ApiDTO;

/**
 * 동영상을 업로드할 때 필요한 Object
 */
public class UploadRequestDto {
    /**
     * 원본 동영상 파일
     */
    Byte originVideo;
    /**
     * 번역 희망 여
     */
    Boolean needTranslate;
}
