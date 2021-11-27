package remora.remora.FrameExtraction;

import java.io.File;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Service;

import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

@Service
public class FrameExtractionService {
    public FrameExtractionResponseDto frameExtract(FrameExtractionRequestDto request) {
        FrameExtractionResponseDto response = new FrameExtractionResponseDto();
        response.success = false;
        response.frameSet = new ArrayList<Picture>();

        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(request.originVideo));
            Picture picture = null;

            while ((picture = grab.getNativeFrame()) != null) {
                response.frameSet.add(picture);
                System.out.println(picture.getWidth() + " " + picture.getHeight());
            }

            for (int i = 0; i < response.frameSet.size(); i += 2) {
                BufferedImage bufferedImage = AWTUtil.toBufferedImage(response.frameSet.get(i));
                ImageIO.write(bufferedImage, "png", new File("C:\\testFrame\\frame" + i + ".png"));
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
