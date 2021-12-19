package remora.remora.Classification;

import org.springframework.web.bind.annotation.RestController;
import remora.remora.Classification.dto.ClassificationRequestDto;
import remora.remora.Classification.dto.ClassificationResponseDto;

@RestController
public class ClassificationController {
    private ClassificationService classificationService = new ClassificationService();

    public ClassificationResponseDto classification(ClassificationRequestDto request){
        /*
            To do : Classification Service Call
         */
        return classificationService.classification(request);
    }
}
