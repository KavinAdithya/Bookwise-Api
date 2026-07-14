package com.techcrack.bookwise.responseHelper;

import org.springframework.http.ResponseEntity;

public class ResponseEntityHelper {
    public static <T>ResponseEntity<ApiResponseEntity<T>> buildSuccessResponse(String message, T data) {
        return ResponseEntity.ok(
                ApiResponseEntity.success(message, data)
            );
    }

    public static ResponseEntity<ApiResponseEntity<Object>> buildInternalServerErrorResponse(String message) {
        return ResponseEntity.internalServerError()
                .body(ApiResponseEntity.failure(message));
    }

}
