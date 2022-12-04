package com.codestates.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404,"Member not Found");

    @Getter
    private int status;

    @Getter
    private String message;

     ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
