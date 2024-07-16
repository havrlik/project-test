package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class CreateDirectoryException extends TestProjectException {

    private final String directory;

    public CreateDirectoryException(String directory) {
        super(
                ErrorCode.DIRECTORY_CREATE_FAIL,
                String.format("Failed to create directory '%s'.", directory)
        );

        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}
