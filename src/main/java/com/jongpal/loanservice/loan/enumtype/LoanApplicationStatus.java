package com.jongpal.loanservice.loan.enumtype;

public enum LoanApplicationStatus {
    APPLIED,     // 신청
    REVIEWING,   // 심사중
    APPROVED,    // 승인
    REJECTED,    // 거절
    EXECUTED,    // 실행
    REPAYING,    // 상환중
    CLOSED       // 종료
}