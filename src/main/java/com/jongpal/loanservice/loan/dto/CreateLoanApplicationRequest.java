package com.jongpal.loanservice.loan.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateLoanApplicationRequest {

    @NotNull // 반드시 있어야 함
    private Long customerId;

    @NotNull
    @Min(1000000) // 최소 100만원
    private Long loanAmount;

    @NotNull
    @Min(1) // 최소 1개월
    private Integer loanTermMonths;
}