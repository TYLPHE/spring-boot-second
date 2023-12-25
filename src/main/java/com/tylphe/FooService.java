package com.tylphe;

import com.tylphe.customer.Customer;
import com.tylphe.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FooService {

    private final Main.Foo foo;


    public FooService(Main.Foo foo) {
        this.foo = foo;
        System.out.println();
    }

    String getFooName() {
        return foo.name();
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer alex = new Customer(
                    "alex",
                    "alex@alex.com",
                    21
            );

            Customer joe = new Customer(
                    "joe",
                    "joe@joe.com",
                    21
            );

            List<Customer> customers = List.of(alex, joe);

            customerRepository.saveAll(customers);
        };
    }
}
