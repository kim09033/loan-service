package com.jongpal.loanservice.loan.repository;

import com.jongpal.loanservice.loan.domain.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}