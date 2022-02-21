package com.jb.SpringProject;

import com.jb.SpringProject.Art.Starter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringProjectApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringProjectApplication.class, args);
		System.out.println(Starter.start);
	}

}
