package remora.remora.FrameExtraction.thread;

import java.io.File;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import io.github.cdimascio.dotenv.Dotenv;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import remora.remora.Common.PicturePair;

public class ExtractionThread extends Thread {
    private int threadNo;
    private int threadSize;
    private int frameInterval;
    private ArrayList<PicturePair> frameSet;
    private String path;
    private Dotenv dotenv;

    public ExtractionThread(int threadNo, int threadSize, int frameInterval, ArrayList<PicturePair> frameSet, String path) {
        this.threadNo = threadNo;
        this.threadSize = threadSize;
        this.frameInterval = frameInterval;
        this.frameSet = frameSet;
        this.path = path;
        this.dotenv = Dotenv.configure().load();
    }

    public void run() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(new File(dotenv.get("VIDEO_PATH") + "req_video" + path)));
            int totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();

            System.out.println("totalFrames : " + totalFrames);

            for (int i = 0, threadIdx = 0; i < totalFrames; i += frameInterval, threadIdx++) {
                if (threadIdx % threadSize == threadNo) {
                    Picture picture = null;

                    if ((picture = grab.seekToFramePrecise(i).getNativeFrame()) != null) {
                        frameSet.add(new PicturePair(i, picture));
                        System.out.println("Extraction : " + i + ", width : " + picture.getWidth() + ", height : " + picture.getHeight());

                        BufferedImage bufferedImage = AWTUtil.toBufferedImage(frameSet.get(frameSet.size() - 1).second());
                        ImageIO.write(bufferedImage, "png", new File(dotenv.get("FRAME_PATH") + frameSet.get(frameSet.size() - 1).first() + ".png"));
                        System.out.println("Write : " + frameSet.get(frameSet.size() - 1).first() + ".png");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
