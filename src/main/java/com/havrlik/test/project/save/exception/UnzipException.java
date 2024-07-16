package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class UnzipException extends TestProjectException {

    private final String workingDirectory;

    public UnzipException(String message, String workingDirectory) {
        super(
                ErrorCode.UNZIP_FAIL,
                String.format("File unzip failed. workingDirectory: '%s'. Message: %s", workingDirectory, message)
        );

        this.workingDirectory = workingDirectory;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
