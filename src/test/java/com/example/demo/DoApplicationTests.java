package com.example.demo;

import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Table(name = "list")
class DoApplicationTests {

	@Test
	void contextLoads() {
	}

}
