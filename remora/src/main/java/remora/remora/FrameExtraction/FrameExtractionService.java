package remora.remora.FrameExtraction;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import remora.remora.Common.PicturePair;
import remora.remora.FrameExtraction.dto.FrameExtractionRequestDto;
import remora.remora.FrameExtraction.dto.FrameExtractionResponseDto;
import remora.remora.FrameExtraction.thread.ExtractionThread;

@Service
public class FrameExtractionService {
    public FrameExtractionResponseDto frameExtract(FrameExtractionRequestDto request) throws InterruptedException {
        FrameExtractionResponseDto response = new FrameExtractionResponseDto();
        int frameInterval = 10;
        int threadSize = 8;
        ExtractionThread[] extractionThread = new ExtractionThread[threadSize];
        boolean isRunning = true;

        response.success = false;
        response.frameSet = new ArrayList<PicturePair>();

        for (int i = 0; i < threadSize; i++) {
            extractionThread[i] = new ExtractionThread(i, threadSize, frameInterval, response, request.originVideo);
            extractionThread[i].start();
        }

        try {
            while (isRunning) {
                Thread.sleep(500);
                isRunning = false;

                for (int i = 0; i < threadSize; i++) {
                    if (extractionThread[i].isAlive()) {
                        isRunning = true;
                    }
                }
            }
        } catch (Exception e) {
            response.success = false;
            response.frameSet = null;
            e.printStackTrace();
        }

        return response;
    }
}
