package remora.remora.FrameExtraction.thread;

import java.io.File;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;

public class ExtractionThread extends Thread {
    private int threadNo;
    private int threadSize;
    private int frameInterval;
    private FrameExtractionResponseDto response;
    File originVideo;

    public ExtractionThread(int threadNo, int threadSize, int frameInterval, FrameExtractionResponseDto response,
            File originVideo) {
        this.threadNo = threadNo;
        this.threadSize = threadSize;
        this.frameInterval = frameInterval;
        this.response = response;
        this.originVideo = originVideo;
    }

    public void run() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(originVideo));
            Picture picture = null;
            int totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();

            System.out.println("totalFrames : " + totalFrames);

            for (int i = 0; i < totalFrames; i += frameInterval) {
                if (i % threadSize == threadNo) {
                    if ((picture = grab.seekToFramePrecise(i).getNativeFrame()) != null) {
                        response.frameSet.add(picture);
                        System.out.println(i + " " + picture.getWidth() + " " + picture.getHeight());

                        BufferedImage bufferedImage = AWTUtil
                                .toBufferedImage(response.frameSet.get((response.frameSet.size() - 1)));
                        ImageIO.write(bufferedImage, "png",
                                new File("C:\\testFrame\\frame" + (response.frameSet.size() - 1) + ".png"));
                    }
                }
            }

            response.success = true;
        } catch (Exception e) {
            response.success = false;
            response.frameSet = null;
            e.printStackTrace();
        }
    }
}
