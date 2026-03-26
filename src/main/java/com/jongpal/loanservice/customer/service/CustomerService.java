package com.jongpal.loanservice.customer.service;

import com.jongpal.loanservice.customer.domain.Customer;
import com.jongpal.loanservice.customer.dto.CreateCustomerRequest;
import com.jongpal.loanservice.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 서비스 계층 (비즈니스 로직 담당)
@RequiredArgsConstructor // final 필드 생성자 자동 생성
public class CustomerService {

    private final CustomerRepository customerRepository;
    // DB 접근 담당 객체 (Repository)

    public Long createCustomer(CreateCustomerRequest request) {

        // 요청 데이터를 기반으로 Customer 객체 생성
        Customer customer = Customer.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .annualIncome(request.getAnnualIncome())
                .jobName(request.getJobName())
                .build();

        // DB 저장 후 생성된 ID 반환
        return customerRepository.save(customer).getId();
    }
}