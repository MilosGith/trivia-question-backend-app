package com.milostrivia.trivia;

import com.milostrivia.trivia.controller.TriviaQuestionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TriviaApplicationTests {

	@Autowired
	private TriviaQuestionController controller;

	@Test
	void contextLoads() {
		assert(controller != null);
	}

}
