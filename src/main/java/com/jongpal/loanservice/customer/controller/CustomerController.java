package com.jongpal.loanservice.customer.controller;

import com.jongpal.loanservice.customer.dto.CreateCustomerRequest;
import com.jongpal.loanservice.customer.service.CustomerService;
import com.jongpal.loanservice.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // REST API 컨트롤러
@RequiredArgsConstructor
@RequestMapping("/customers") // 기본 URL
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 고객 생성 API
     * POST /customers
     */
    @PostMapping
    public ApiResponse<Long> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        // 요청을 Service로 넘기고
        // 결과를 ApiResponse로 감싸서 반환
        return ApiResponse.success(customerService.createCustomer(request));
    }
}