package com.techcrack.bookwise.exceptions.handler;

import com.techcrack.bookwise.responseHelper.ApiResponseEntity;
import com.techcrack.bookwise.responseHelper.ResponseEntityHelper;
import com.techcrack.bookwise.utils.BaseLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseLogger<GlobalExceptionHandler> {

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
