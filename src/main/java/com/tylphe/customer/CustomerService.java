package com.tylphe.customer;

import com.tylphe.exception.BadRequestException;
import com.tylphe.exception.DuplicateResourceException;
import com.tylphe.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDAO.selectCustomerById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    public void addCustomer(CustomerRegistrationRequest customer) {

        // check if email exists
        if (customerDAO.existsPersonWithEmail(customer.email())) {
            throw new DuplicateResourceException("email already taken");
        }

        // add
        customerDAO.insertCustomer(new Customer(
                customer.name(),
                customer.email(),
                customer.age()
        ));
    }

    public void deleteCustomerById(Integer id) {

        // Check if id exists
        Customer customer = customerDAO.selectCustomerById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found"));

        customerDAO.removeCustomer(customer);
    }

    public void updateCustomerById(Integer id, CustomerRegistrationRequest register) {

        // Check if id exists
        Customer customer = customerDAO.selectCustomerById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found"));

        boolean updated = false;
        if (register.age() != null && !register.age().equals(customer.getAge())) {
            customer.setAge(register.age());
            updated = true;
        }

        if (register.email() != null && ! register.email().equals(customer.getEmail())) {
            customer.setEmail(register.email());
            updated = true;
        }

        if (register.name() != null && !register.name().equals(customer.getName())) {
            customer.setName(register.name());
            updated = true;
        }

        if (!updated) {
            throw new BadRequestException("no data changes found");
        }

        customerDAO.updateCustomer(customer);
    }
}
