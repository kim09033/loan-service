# 🏦 Loan Service Project

## 📌 프로젝트 소개

대출 신청부터 심사, 승인, 실행, 상환까지의 과정을 상태 기반으로 관리하는 대출 서비스입니다.
금융 시스템의 기본 흐름을 이해하고, 이를 객체지향적으로 설계하는 것을 목표로 개발했습니다.

---

## 🛠 기술 스택

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Lombok

---

## 📌 주요 기능

### 1. 고객 생성

* 고객 정보를 등록

### 2. 대출 신청

* 고객 기반 대출 신청
* 상태: `APPLIED`

### 3. 심사

* `APPLIED → REVIEWING`

### 4. 승인 / 거절

* `REVIEWING → APPROVED / REJECTED`

### 5. 대출 실행

* `APPROVED → EXECUTED`

### 6. 상환 시작

* `EXECUTED → REPAYING`

---

## 🔄 대출 상태 흐름

APPLIED → REVIEWING → APPROVED → EXECUTED → REPAYING

---

## 📌 API 목록

### 고객

* POST /customers

### 대출

* POST /loans/apply
* POST /loans/{id}/review
* POST /loans/{id}/approve
* POST /loans/{id}/reject
* POST /loans/{id}/execute
* POST /loans/{id}/start-repayment

---

## 📌 프로젝트 구조

Controller → Service → Repository → Entity

---

## 💡 설계 포인트

* 상태 기반으로 대출 흐름을 제어
* 잘못된 상태 변경 방지 (ex. 승인 없이 실행 불가)
* 공통 응답 구조(ApiResponse) 적용
* 글로벌 예외 처리로 API 일관성 확보

---

## 🚀 실행 방법

1. 프로젝트 실행
2. http://localhost:8080/h2-console 접속
3. 테스트는 test.http 파일 사용

---

## 💬 회고

단순 CRUD를 넘어서 상태 기반 금융 시스템을 구현하며
비즈니스 로직을 어떻게 코드로 표현하는지 이해할 수 있었습니다.
