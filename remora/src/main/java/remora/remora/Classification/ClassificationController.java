package remora.remora.Classification;

import remora.remora.Classification.dto.ClassificationRequestDto;
import remora.remora.Classification.dto.ClassificationResponseDto;

public class ClassificationController {
    ClassificationService classificationService = new ClassificationService();

    public ClassificationResponseDto classification(ClassificationRequestDto request) {
        return classificationService.classification(request);
    }
}
