package com.exams.spservice;

//import com.exams.spservice.model.Product;
//import com.exams.spservice.repository.ProductRepository;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

//import java.math.BigDecimal;

@SpringBootApplication
public class SpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
//		return args -> {
//			for (int i = 1; i <= 10; i++) {
//				productRepository.save(
//						new Product(null,
//								String.format("Product %s", i),
//								"https://as2.ftcdn.net/v2/jpg/07/23/65/75/1000_F_723657558_jNX9NgKrIfQziXDpyHYu3PH2YVcAQlLm.jpg",
//								String.format("Description of product %s", i),
//								BigDecimal.valueOf(i),
//								false
//					)
//				);
//			}
//		};
//	}

}
