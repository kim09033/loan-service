package com.jongpal.loanservice.loan.service;

import com.jongpal.loanservice.customer.domain.Customer;
import com.jongpal.loanservice.customer.repository.CustomerRepository;
import com.jongpal.loanservice.loan.domain.LoanApplication;
import com.jongpal.loanservice.loan.dto.CreateLoanApplicationRequest;
import com.jongpal.loanservice.loan.enumtype.LoanApplicationStatus;
import com.jongpal.loanservice.loan.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service // 서비스 계층: 비즈니스 로직 담당
@RequiredArgsConstructor // final 필드 생성자 자동 주입
@Transactional // 이 클래스의 메서드들은 트랜잭션 안에서 동작
public class LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final CustomerRepository customerRepository;

    /**
     * 대출 신청
     * 1. 고객 존재 여부 확인
     * 2. LoanApplication 객체 생성
     * 3. 상태를 APPLIED로 저장
     * 4. 저장 후 생성된 대출 ID 반환
     */
    public Long createLoanApplication(CreateLoanApplicationRequest request) {
        // customerId로 고객 조회
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다."));

        // 대출 신청 객체 생성
        LoanApplication loanApplication = LoanApplication.builder()
                .customer(customer) // 대출 신청한 고객 연결
                .loanAmount(request.getLoanAmount()) // 대출 금액
                .loanTermMonths(request.getLoanTermMonths()) // 대출 기간(개월)
                .status(LoanApplicationStatus.APPLIED) // 최초 상태는 신청(APPLIED)
                .appliedAt(LocalDateTime.now()) // 신청 시간
                .build();

        // DB 저장 후 생성된 id 반환
        return loanApplicationRepository.save(loanApplication).getId();
    }

    /**
     * 심사 시작
     * APPLIED 상태일 때만 REVIEWING으로 변경 가능
     */
    public void startReview(Long loanId) {
        // loanId로 대출 신청 조회
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대출 신청입니다."));

        // 현재 상태가 APPLIED가 아니면 심사 시작 불가
        if (loanApplication.getStatus() != LoanApplicationStatus.APPLIED) {
            throw new IllegalStateException("신청 상태에서만 심사를 시작할 수 있습니다.");
        }

        // 상태 변경: APPLIED -> REVIEWING
        loanApplication.setStatus(LoanApplicationStatus.REVIEWING);
    }

    /**
     * 승인 처리
     * REVIEWING 상태일 때만 APPROVED로 변경 가능
     */
    public void approve(Long loanId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대출 신청입니다."));

        // 현재 상태가 REVIEWING가 아니면 승인 불가
        if (loanApplication.getStatus() != LoanApplicationStatus.REVIEWING) {
            throw new IllegalStateException("심사중 상태에서만 승인할 수 있습니다.");
        }

        // 상태 변경: REVIEWING -> APPROVED
        loanApplication.setStatus(LoanApplicationStatus.APPROVED);
    }

    /**
     * 거절 처리
     * REVIEWING 상태일 때만 REJECTED로 변경 가능
     */
    public void reject(Long loanId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대출 신청입니다."));

        // 현재 상태가 REVIEWING가 아니면 거절 불가
        if (loanApplication.getStatus() != LoanApplicationStatus.REVIEWING) {
            throw new IllegalStateException("심사중 상태에서만 거절할 수 있습니다.");
        }

        // 상태 변경: REVIEWING -> REJECTED
        loanApplication.setStatus(LoanApplicationStatus.REJECTED);
    }
    /**
     * 대출 실행
     * APPROVED 상태일 때만 EXECUTED로 변경 가능
     */
    public void execute(Long loanId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대출 신청입니다."));

        // 현재 상태가 APPROVED가 아니면 실행 불가
        if (loanApplication.getStatus() != LoanApplicationStatus.APPROVED) {
            throw new IllegalStateException("승인 상태에서만 대출 실행할 수 있습니다.");
        }

        // 상태 변경: APPROVED -> EXECUTED
        loanApplication.setStatus(LoanApplicationStatus.EXECUTED);
    }
    /**
     * 상환 시작
     * EXECUTED 상태일 때만 REPAYING으로 변경 가능
     */
    public void startRepayment(Long loanId) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대출 신청입니다."));

        // 현재 상태가 EXECUTED가 아니면 상환 시작 불가
        if (loanApplication.getStatus() != LoanApplicationStatus.EXECUTED) {
            throw new IllegalStateException("실행 상태에서만 상환을 시작할 수 있습니다.");
        }

        // 상태 변경: EXECUTED -> REPAYING
        loanApplication.setStatus(LoanApplicationStatus.REPAYING);
    }

}