package remora.remora.FrameExtraction;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import remora.remora.Common.PicturePair;
import remora.remora.FrameExtraction.thread.ExtractionThread;

@Service
public class FrameExtractionService {
    public ArrayList<PicturePair> frameExtract(String path) throws InterruptedException {
        ArrayList<PicturePair> ret = new ArrayList<PicturePair>();
        int frameInterval = 150;
        int threadSize = 2;
        ExtractionThread[] extractionThread = new ExtractionThread[threadSize];
        Boolean isRunning = true;

        for (int i = 0; i < threadSize; i++) {
            extractionThread[i] = new ExtractionThread(i, threadSize, frameInterval, ret, path);
            extractionThread[i].start();
        }

        while (isRunning) {
            Thread.sleep(500);
            isRunning = false;

            for (int i = 0; i < threadSize; i++) {
                if (extractionThread[i].isAlive()) {
                    isRunning = true;
                }
            }
        }

        return ret;
    }
}
