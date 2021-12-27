package remora.remora.FrameExtraction.thread;

import java.io.File;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import io.github.cdimascio.dotenv.Dotenv;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;
import remora.remora.Common.PicturePair;

public class ExtractionThread extends Thread {
    private int threadNo;
    private int threadSize;
    private int frameInterval;
    private FrameExtractionResponseDto response;
    File originVideo;
    Dotenv dotenv = Dotenv.configure().load();

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
            int totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();
            
            System.out.println("totalFrames : " + totalFrames);
            
            for (int i = 0, threadIdx = 0; i < totalFrames; i += frameInterval, threadIdx++) {
                if (threadIdx % threadSize == threadNo) {
                    Picture picture = null;
                    if ((picture = grab.seekToFramePrecise(i).getNativeFrame()) != null) {
                        response.frameSet.add(new PicturePair(i, picture));
                        System.out.println("Extraction : " + i + ", width : " + picture.getWidth() + ", height : "
                                + picture.getHeight());

                    BufferedImage bufferedImage = AWTUtil.toBufferedImage(response.frameSet.get(response.frameSet.size() - 1).second());
                    ImageIO.write(bufferedImage, "png",
                            new File(dotenv.get("FRAME_PATH") + response.frameSet.get(response.frameSet.size() - 1).first() + ".png"));
                    System.out.println("Write : " + response.frameSet.get(response.frameSet.size() - 1).first() + ".png");
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
