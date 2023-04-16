package com.musala.droneservice.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private final String message;
    private T data;

    public static class ApiResponseBuilder<T> {
        private final String message;
        private T data;

        public ApiResponseBuilder(String message) {
            this.message = message;
        }

        public ApiResponseBuilder setData(T data) {
            this.data = data;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<T>(this);
        }
    }

    private ApiResponse(ApiResponseBuilder builder) {
        this.message = builder.message;
        this.data = (T) builder.data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
