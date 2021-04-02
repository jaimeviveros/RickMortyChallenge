package rick.morty.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RickAndMortyChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RickAndMortyChallengeApplication.class, args);
	}

}
