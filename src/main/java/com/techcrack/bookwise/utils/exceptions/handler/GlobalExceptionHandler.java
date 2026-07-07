package com.techcrack.bookwise.utils.exceptions.handler;

import com.techcrack.bookwise.utils.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.utils.responseHelper.ResponseEntityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseEntity<Object>> generalizedException(Exception ex) {

        return ResponseEntityHelper.buildInternalServerErrorResponse(
                ex.getMessage()
        );
    }

}
