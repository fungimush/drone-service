package com.musala.droneservice.utils.response;

import lombok.Builder;

@Builder
public class DispatchApiResponse<T> {
    private boolean success;
    private String message;
    private String statusCode;
    private T data;

    public DispatchApiResponse() {
    }

    public DispatchApiResponse(boolean success, String statusCode, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DroneApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", data=" + data +
                '}';
    }
}
