package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.controller.FoodController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FoodRecipeApplicationTests {

	@Autowired
	FoodController foodController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(foodController).isNotNull();
	}

}
