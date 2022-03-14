package com.tutorial.oauth2.keycloak;

import com.tutorial.oauth2.keycloak.model.Foo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class KeycloarkApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KeycloarkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Foo> foos = Stream.of(new Foo(1,"foo 1"),new Foo(2,"foo 2"), new Foo(3,"foo 3")).collect(Collectors.toList());
		List<Foo> nueva=foos.stream().filter(item->item.getId()==1).collect(Collectors.toList());
		nueva.stream().forEach(System.out::println);
	}
}
