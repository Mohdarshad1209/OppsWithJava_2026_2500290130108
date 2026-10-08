package com.example.MyFirstSpringProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFirstSpringProjectApplication implements CommandLineRunner {
	@Autowired
	HelloWorld helloWorld;

	public MyFirstSpringProjectApplication(HelloWorld helloWorld) {
		this.helloWorld = helloWorld;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyFirstSpringProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(helloWorld.sayHello());
	}
}