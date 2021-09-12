package com.zxsample.demo.api.model;

import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .success(true).data(data)
                .build();
    }

    public static <T> Result<T> failed(String errorCode, String message) {
        return Result.<T>builder()
                .success(false).errorCode(errorCode).message(message)
                .build();
    }

    private boolean success;
    private String errorCode;
    private String message;
    private T data;
}
