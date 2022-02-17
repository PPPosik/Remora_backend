package remora.remora.Upload.dto;

import java.util.List;

public class UploadResponseDto {
    private Boolean success;
    private String message;
    private int code;
    private boolean needTranslation;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNeedTranslation(boolean needTranslation) {
        this.needTranslation = needTranslation;
    }
}
