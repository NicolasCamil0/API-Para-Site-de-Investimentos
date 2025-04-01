package com.mio.todosimple;

import com.mio.todosimple.Selic.Logica.LogicaSelicUser;
import com.mio.todosimple.Selic.Service.SelicService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.mio.todosimple")
public class TodosimpleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TodosimpleApplication.class, args);

		SelicService selicService = context.getBean(SelicService.class);
		LogicaSelicUser logicaSelicUser = new LogicaSelicUser(selicService);

		logicaSelicUser.selicUserValue();
	}

}
