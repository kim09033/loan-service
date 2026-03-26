package com.jongpal.loanservice.loan.controller;

import com.jongpal.loanservice.global.common.ApiResponse;
import com.jongpal.loanservice.loan.dto.CreateLoanApplicationRequest;
import com.jongpal.loanservice.loan.service.LoanApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    /**
     * 대출 신청
     */
    @PostMapping("/apply")
    public ApiResponse<Long> apply(@Valid @RequestBody CreateLoanApplicationRequest request) {
        return ApiResponse.success(
                loanApplicationService.createLoanApplication(request)
        );
    }

    /**
     * 심사 시작
     */
    @PostMapping("/{id}/review")
    public ApiResponse<Void> startReview(@PathVariable Long id) {
        loanApplicationService.startReview(id);
        return ApiResponse.success(); // 성공 응답
    }

    /**
     * 승인
     */
    @PostMapping("/{id}/approve")
    public ApiResponse<Void> approve(@PathVariable Long id) {
        loanApplicationService.approve(id);
        return ApiResponse.success();
    }

    /**
     * 거절
     */
    @PostMapping("/{id}/reject")
    public ApiResponse<Void> reject(@PathVariable Long id) {
        loanApplicationService.reject(id);
        return ApiResponse.success();
    }

    /**
     * 실행
     */
    @PostMapping("/{id}/execute")
    public ApiResponse<Void> execute(@PathVariable Long id) {
        loanApplicationService.execute(id);
        return ApiResponse.success();
    }

    /**
     * 상환 시작
     */
    @PostMapping("/{id}/start-repayment")
    public ApiResponse<Void> startRepayment(@PathVariable Long id) {
        loanApplicationService.startRepayment(id);
        return ApiResponse.success();
    }
}