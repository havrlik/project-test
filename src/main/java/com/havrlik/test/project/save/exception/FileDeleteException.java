package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class FileDeleteException extends TestProjectException {

    private final String workingDirectory;

    public FileDeleteException(String workingDirectory) {
        super(
                ErrorCode.FILE_DELETE_FAIL,
                String.format("File delete failed. Working directory: '%s'.", workingDirectory)
        );

        this.workingDirectory = workingDirectory;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }
}
