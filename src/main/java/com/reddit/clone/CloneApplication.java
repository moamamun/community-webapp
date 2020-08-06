package com.reddit.clone;

import com.reddit.clone.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
// With import at application startup spring will invoke spring fox -->
// will scan all the rest controllers components in our app and the corresponding dtos for this rest controllers
//--> generate the rest API documentation for all of these
@Import(SwaggerConfig.class)
public class CloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneApplication.class, args);
	}

}
