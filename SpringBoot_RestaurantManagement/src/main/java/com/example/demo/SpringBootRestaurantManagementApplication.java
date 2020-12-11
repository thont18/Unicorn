package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.property.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class SpringBootRestaurantManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestaurantManagementApplication.class, args);
		System.out.println("Done !!");
	}

}
