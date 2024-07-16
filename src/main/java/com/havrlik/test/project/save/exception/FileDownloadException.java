package com.havrlik.test.project.save.exception;

import com.havrlik.test.project.save.exceptionhandler.ErrorCode;
import com.havrlik.test.project.save.exceptionhandler.TestProjectException;

public class FileDownloadException extends TestProjectException {

    private final String fileUrl;

    public FileDownloadException(String message, String fileUrl) {
        super(
                ErrorCode.FILE_DOWNLOAD_FAIL,
                String.format("File download failed. URL: '%s'. Message: %s", fileUrl, message)
        );

        this.fileUrl = fileUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }
}
