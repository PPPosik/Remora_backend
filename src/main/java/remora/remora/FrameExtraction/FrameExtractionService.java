package remora.remora.FrameExtraction;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import remora.remora.FrameExtraction.thread.ExtractionThread;
import remora.remora.Exception.NotFoundVideoException;

@Service
public class FrameExtractionService {
    @Value("${video-path}")
    String videoPath;
    @Value("${frame-path}")
    String framePath;

    private static final int threadSize = 2;
    private static final int frameInterval = 150;
    private Logger log = LoggerFactory.getLogger(getClass());

    public ArrayList<Integer> frameExtract(String path) throws Exception {
        ArrayList<Integer> ret = new ArrayList<>();
        ExtractionThread[] extractionThread = new ExtractionThread[threadSize];

        deleteFrames();

        for (int i = 0; i < threadSize; i++) {
            extractionThread[i] = new ExtractionThread(i, threadSize, frameInterval, videoPath, framePath, ret, path);
            extractionThread[i].start();
        }

        for (ExtractionThread thread : extractionThread) {
            thread.join();
        }

        if (ret.size() == 0) {
            log.debug("Extraction Fail, Video Not Found");
            throw new NotFoundVideoException();
        }

        return ret;
    }

    public int getFrameInterval() {
        return frameInterval;
    }

    public void deleteFrames() {
        File[] files = new File(framePath).listFiles();

        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(".gitkeep")) {
                    file.delete();
                }
            }
        }
    }
}
