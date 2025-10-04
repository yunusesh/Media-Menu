package product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MediamenuBackendApplication {

	public static void main(String[] args) {
        System.out.println("hello world");

        SpringApplication.run(MediamenuBackendApplication.class, args);
	}
}
