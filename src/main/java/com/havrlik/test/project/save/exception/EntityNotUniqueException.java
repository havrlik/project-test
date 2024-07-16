package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class EntityNotUniqueException extends TestProjectException {

    private final String type;
    private final String code;

    public EntityNotUniqueException(Class type, String code) {
        super(
                ErrorCode.ENTITY_NOT_UNIQUE,
                String.format("Entity '%s' with code '%s' is not unique.", type.getSimpleName(), code)
        );

        this.type = type.getSimpleName();
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}
