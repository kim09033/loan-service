package com.jongpal.loanservice.loan.domain;

import com.jongpal.loanservice.customer.domain.Customer;
import com.jongpal.loanservice.loan.enumtype.LoanApplicationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Long loanAmount;

    private Integer loanTermMonths;

    @Enumerated(EnumType.STRING)
    private LoanApplicationStatus status;

    private LocalDateTime appliedAt;

    public void setStatus(LoanApplicationStatus status) {
        this.status = status;
    }
}

