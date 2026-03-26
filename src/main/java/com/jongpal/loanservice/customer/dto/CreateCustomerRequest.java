package com.jongpal.loanservice.customer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter // getter 자동 생성
public class CreateCustomerRequest {

    @NotBlank // null 또는 빈값이면 에러
    private String name;

    @NotBlank
    private String phoneNumber;

    @Min(0) // 0 이상만 허용 (음수 방지)
    private Integer annualIncome;

    @NotBlank
    private String jobName;
}