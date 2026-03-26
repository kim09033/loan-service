package com.jongpal.loanservice.global.exception;

import com.jongpal.loanservice.global.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 처리
public class GlobalExceptionHandler {

    /**
     * 잘못된 값 (ex. 존재하지 않는 ID)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // 404
                .body(ApiResponse.fail(e.getMessage())); // 메시지 반환
    }

    /**
     * 상태 흐름 오류 (ex. 승인 안 된 대출 실행)
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(ApiResponse.fail(e.getMessage()));
    }

    /**
     * DTO 검증 실패 (ex. name 빈값)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {

        // 첫 번째 에러 메시지 가져오기
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .orElse("잘못된 요청입니다.");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 400
                .body(ApiResponse.fail(message));
    }

    /**
     * 그 외 모든 예외 (서버 오류)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(ApiResponse.fail("서버 내부 오류가 발생했습니다."));
    }
}