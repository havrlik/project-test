package com.havrlik.test.project.save.exceptionhandler;

public abstract class TestProjectException extends RuntimeException {

    private final ErrorCode errorCode;

    public TestProjectException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
