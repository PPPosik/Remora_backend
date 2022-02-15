package remora.remora.FrameExtraction;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import remora.remora.Common.PicturePair;
import remora.remora.FrameExtraction.thread.ExtractionThread;

@Service
public class FrameExtractionService {
    public ArrayList<PicturePair> frameExtract(String path) throws Exception {
        int threadSize = 2;
        int frameInterval = 150;
        ArrayList<PicturePair> ret = new ArrayList<>();
        ExtractionThread[] extractionThread = new ExtractionThread[threadSize];

        for (int i = 0; i < threadSize; i++) {
            extractionThread[i] = new ExtractionThread(i, threadSize, frameInterval, ret, path);
            extractionThread[i].start();
        }

        for (ExtractionThread thread : extractionThread) {
            thread.join();
        }

        return ret;
    }
}
