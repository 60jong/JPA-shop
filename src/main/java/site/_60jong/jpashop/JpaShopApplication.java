package site._60jong.jpashop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import site._60jong.jpashop.application.service.ItemService;

@Slf4j
@SpringBootApplication
public class JpaShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaShopApplication.class, args);
	}

	@Bean
	public AbstractJackson2HttpMessageConverter converter() {
		log.info("converter added");
		return new MappingJackson2HttpMessageConverter();
	}
}
