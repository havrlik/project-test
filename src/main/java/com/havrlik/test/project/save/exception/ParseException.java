package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class ParseException extends TestProjectException {

    public ParseException(String message) {
        super(
                ErrorCode.PARSE_FAIL,
                String.format("Parse failed. Message: %s", message)
        );
    }
}
