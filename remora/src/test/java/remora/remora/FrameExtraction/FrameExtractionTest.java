package remora.remora.FrameExtraction;

import io.github.cdimascio.dotenv.Dotenv;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class FrameExtractionTest {
    private static final Dotenv dotenv = Dotenv.load();
    FrameExtractionService frameExtractionService;

    @BeforeEach
    public void beforeEach() {
        frameExtractionService = new FrameExtractionService();
    }

    @AfterEach
    public void afterEach() {
        File[] files = new File(dotenv.get("FRAME_PATH")).listFiles();

        for (File file : files) {
            file.delete();
        }
    }

    @Test
    public void frameExtract() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(new File(dotenv.get("VIDEO_PATH") + "1.mp4")));
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
        try {
            frameExtractionService.frameExtract("-1");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("Video Not Found");
        }
    }
}
