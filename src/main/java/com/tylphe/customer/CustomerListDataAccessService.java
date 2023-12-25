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
}
