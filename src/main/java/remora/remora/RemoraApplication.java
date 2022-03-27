package remora.remora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RemoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoraApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfig(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://remora-223.herokuapp.com, http://cors-anywhere.herokuapp.com. http://localhost:3000")
						.allowedMethods("GET", "POST");
			}
		};
	}

}
