package com.tylphe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean("foo")
    public Foo getFoo() {
        return new Foo("bar");
    }

    record Foo(String name) {

    }
}
