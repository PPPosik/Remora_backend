package remora.remora.FrameExtraction.thread;

import java.io.File;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class ExtractionThread extends Thread {
    private final int threadNo;
    private final int threadSize;
    private final int frameInterval;
    private final String videoPath;
    private final String framePath;
    private final ArrayList<Integer> frameSet;
    private final String path;

    public ExtractionThread(int threadNo, int threadSize, int frameInterval, String videoPath, String framePath, ArrayList<Integer> frameSet, String path) {
        this.threadNo = threadNo;
        this.threadSize = threadSize;
        this.frameInterval = frameInterval;
        this.videoPath = videoPath;
        this.framePath = framePath;
        this.frameSet = frameSet;
        this.path = path;
    }

    public void run() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(new File(videoPath + path + ".mp4")));
            int totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();

            System.out.println("totalFrames : " + totalFrames);

            for (int i = 0, threadIdx = 0; i < totalFrames; i += frameInterval, threadIdx++) {
                if (threadIdx % threadSize == threadNo) {
                    Picture picture;

                    if ((picture = grab.seekToFramePrecise(i).getNativeFrame()) != null) {
                        frameSet.add(i);
                        System.out.println("Extraction : " + i + ", width : " + picture.getWidth() + ", height : " + picture.getHeight());

                        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                        ImageIO.write(bufferedImage, "png", new File(framePath + path + "_" + i + ".png"));
                        System.out.println("Write : " + path + "_" + i + ".png");
                    }
                }
            }
        } catch (IOException | JCodecException e) {
            frameSet.clear();
            e.printStackTrace();
        }
    }
}
