package remora.remora.Health;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 서버가 정상적으로 동작하는 지를 확인하기 위한 Healthy Controller
 */
@RestController
public class HealthController {
    /**
     *
     * @return Healthy
     * @throws Exception
     */
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() throws Exception {
        return "Healthy";
    }
}
