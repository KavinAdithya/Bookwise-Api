package com.techcrack.bookwise.responseHelper;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techcrack.bookwise.constans.ApplicationData;

@JsonPropertyOrder({
		"success",
		"message",
		"data",
		"timestamp",
})
public record ApiResponseEntity<T>(boolean success, String message, T data, LocalDateTime timestamp) {

	public static <T> ApiResponseEntity<T> success(String message, T data) {
		return new ApiResponseEntity<T>(
				true,
				message,
				data,
				ApplicationData.SYSTEM_DATE
		);
	}

	public static  ApiResponseEntity<Object> failure(String message) {
		return new ApiResponseEntity<>(
				false,
				message,
				null,
				ApplicationData.SYSTEM_DATE
		);

	}
}
