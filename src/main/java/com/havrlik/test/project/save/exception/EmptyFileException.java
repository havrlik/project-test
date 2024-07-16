package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class EmptyFileException extends TestProjectException {

    private final String workingDirectory;

    public EmptyFileException(String workingDirectory) {
        super(
                ErrorCode.EMPTY_FILE,
                String.format("The file to be unzipped do not contains any item inside. Working directory: '%s'.", workingDirectory)
        );

        this.workingDirectory = workingDirectory;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
