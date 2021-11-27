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
            int totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();
            int frameInterval = 10;
            Picture picture = null;

            System.out.println("totalFrames : " + totalFrames);

            for (int i = 0; i < totalFrames; i += frameInterval) {
                if ((picture = grab.seekToFramePrecise(i).getNativeFrame()) != null) {
                    response.frameSet.add(picture);
                    System.out.println(i + " " + picture.getWidth() + " " + picture.getHeight());

                    BufferedImage bufferedImage = AWTUtil
                            .toBufferedImage(response.frameSet.get((response.frameSet.size() - 1)));
                    ImageIO.write(bufferedImage, "png",
                            new File("C:\\testFrame\\frame" + (response.frameSet.size() - 1) + ".png"));
                }
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
