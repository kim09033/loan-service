package com.jongpal.loanservice.customer.repository;

import com.jongpal.loanservice.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}