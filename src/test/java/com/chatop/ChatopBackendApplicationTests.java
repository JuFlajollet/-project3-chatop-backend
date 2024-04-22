package com.chatop;

import com.chatop.controller.AuthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ChatopBackendApplicationTests {

	@Autowired
	private AuthController authController;

	@Test
	void contextLoads() {
		assertThat(authController).isNotNull();
	}
}
