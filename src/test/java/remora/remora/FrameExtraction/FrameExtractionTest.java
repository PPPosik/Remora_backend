package remora.remora.FrameExtraction;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class FrameExtractionTest {
    @Value("${video-path}")
    String videoPath;
    @Value("${frame-path}")
    String framePath;

    @Autowired
    FrameExtractionService frameExtractionService;

    @AfterEach
    public void afterEach() {
        File[] files = new File(framePath).listFiles();

        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(".gitkeep")) {
                    file.delete();
                }
            }
        }
    }

    @Test
    public void frameExtract() throws Exception{
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(new File(videoPath + "1.mp4")));
            double totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();

            ArrayList<Integer> result = frameExtractionService.frameExtract("1");
            assertThat(result.size()).isEqualTo((int) Math.ceil(totalFrames / frameExtractionService.getFrameInterval()));
    }

    @Test
    public void frameExtractVideoNotFound() {
        assertThrows(Exception.class, () -> frameExtractionService.frameExtract("-1"));
    }
}
