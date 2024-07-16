package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class ZipEntryDirectoryException extends TestProjectException {

    private final String directory;
    private final String name;

    public ZipEntryDirectoryException(String directory, String name) {
        super(
                ErrorCode.ZIP_ENTRY_NOT_CORRECT_DIRECTORY,
                String.format("Zip entry has not correct directory: '%s', name: '%s'.", directory, name)
        );

        this.directory = directory;
        this.name = name;
    }

    public String getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }
}
