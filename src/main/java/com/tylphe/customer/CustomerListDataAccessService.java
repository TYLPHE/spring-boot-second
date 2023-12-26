package com.tylphe.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO {

    // db
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(
                1,
                "alex",
                "alex@alex.com",
                21
        );

        Customer joe = new Customer(
                2,
                "joe",
                "joe@joe.com",
                21
        );

        customers.add(alex);
        customers.add(joe);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {

        // I don't need anything here since I directly update it in CustomerService.
        // Will include my work anyway since it still works.
        Customer existingCustomer = customers.stream()
                .filter(c -> c.getId().equals(customer.getId()))
                .findFirst()
                .orElseThrow();

        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAge(customer.getAge());
        existingCustomer.setName(customer.getName());
    }


}
