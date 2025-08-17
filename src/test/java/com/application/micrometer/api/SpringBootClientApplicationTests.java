package com.application.micrometer.api;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class SpringBootClientApplicationTests {

	@Test
	public void stringIsHello() {
		assertThat("Hello", is("Hello"));
	}
}

