package dev.ganeshpc.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.ganeshpc.productservice.models.Price;
import dev.ganeshpc.productservice.repositories.PriceRepository;

@SpringBootApplication
public class ProductserviceApplication // implements CommandLineRunner
{

	// private PriceRepository priceRepository;

	// public ProductserviceApplication(PriceRepository priceRepository) {
	// this.priceRepository = priceRepository;
	// }

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// Price price = new Price(44);
	// this.priceRepository.save(price);
	// }

}
