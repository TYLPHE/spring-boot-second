package com.tylphe.customer;

public record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age
) {
}
