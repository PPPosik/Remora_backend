package remora.remora.FrameExtraction;

import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    FrameExtractionService frameExtractionService;

    @BeforeEach
    public void beforeEach() {
        frameExtractionService.deleteFrames();
    }

    @Test
    public void frameExtract() {
        try {
            System.out.println("videoPath = " + videoPath);
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(new File(videoPath + "1.mp4")));
            double totalFrames = grab.getVideoTrack().getMeta().getTotalFrames();

            ArrayList<Integer> result = frameExtractionService.frameExtract("1");
            assertThat(result.size()).isEqualTo((int) Math.ceil(totalFrames / frameExtractionService.getFrameInterval()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void frameExtractVideoNotFound() {
        assertThrows(Exception.class, () -> frameExtractionService.frameExtract("-1"));
    }
}
