package com.mio.todosimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.mio.todosimple")
public class TodosimpleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TodosimpleApplication.class, args);
	}

}
