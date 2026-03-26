package com.jongpal.loanservice.global.common;

import lombok.Getter;

@Getter // getter 자동 생성
public class ApiResponse<T> {

    private final boolean success; // 성공 여부
    private final T data;          // 실제 응답 데이터
    private final String message;  // 에러 메시지 또는 추가 메시지

    // 생성자 (외부에서 직접 생성 못하게 private)
    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    /**
     * 성공 응답 (데이터 포함)
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    /**
     * 성공 응답 (데이터 없음)
     */
    public static ApiResponse<Void> success() {
        return new ApiResponse<>(true, null, null);
    }

    /**
     * 실패 응답
     */
    public static ApiResponse<Void> fail(String message) {
        return new ApiResponse<>(false, null, message);
    }
}