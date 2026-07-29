package com.techcrack.bookwise.exceptions.handler;

import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.AbstractLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler extends AbstractLogger<GlobalExceptionHandler> {

    public GlobalExceptionHandler() {
        super(GlobalExceptionHandler.class);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseEntity<Object>> generalizedException(Exception ex) {
        logger.error(Arrays.toString(ex.getStackTrace()));
        return ResponseEntityHelper.buildInternalServerErrorResponse(
                ex.getMessage()
        );
    }

}
