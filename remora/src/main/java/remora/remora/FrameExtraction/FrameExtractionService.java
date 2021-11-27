package remora.remora.FrameExtraction;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.springframework.stereotype.Service;

import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

@Service
public class FrameExtractionService {
    public FrameExtractionResponseDto frameExtract(FrameExtractionRequestDto request) {
        FrameExtractionResponseDto response = new FrameExtractionResponseDto();

        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(request.originVideo));
            Picture picture = null;

            while ((picture = grab.getNativeFrame()) != null) {
                System.out.println(picture.getWidth() + "x" + picture.getHeight() + " " + picture.getColor());
                response.frameSet.add(picture);
            }

            response.success = true;
        } catch (Exception e) {
            response.success = false;
            response.frameSet = null;
            e.printStackTrace();
        }

        return response;
    }
}
