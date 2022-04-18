package remora.remora.Health;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://remora-223.herokuapp.com, http://localhost:3000")
public class HealthController {
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() throws Exception {
        return "Healthy";
    }
}
