package com.havrlik.test.project.save.exceptionhandler;

import com.havrlik.test.project.save.exception.CreateDirectoryException;
import com.havrlik.test.project.save.exception.EmptyFileException;
import com.havrlik.test.project.save.exception.EntityNotUniqueException;
import com.havrlik.test.project.save.exception.FileDeleteException;
import com.havrlik.test.project.save.exception.FileDownloadException;
import com.havrlik.test.project.save.exception.ParseException;
import com.havrlik.test.project.save.exception.UnzipException;
import com.havrlik.test.project.save.exception.ZipEntryDirectoryException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SaveExceptionHandler {

    public SaveExceptionHandler() {  }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(EntityNotUniqueException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleEntityNotUniqueException(EntityNotUniqueException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "Entity is not unique",
                e.getMessage()
        );
//        LOGGER.info(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(FileDeleteException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleFileDeleteException(FileDeleteException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "File delete failed",
                e.getMessage()
        );
        info.addMeta("workingDirectory", e.getWorkingDirectory());
//        LOGGER.error(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(FileDownloadException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleFileDownloadException(FileDownloadException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "File download failed",
                e.getMessage()
        );
        info.addMeta("fileUrl", e.getFileUrl());
//        LOGGER.info(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(EmptyFileException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleEmptyFileException(EmptyFileException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "The file to be unzipped do not contains any item inside",
                e.getMessage()
        );
        info.addMeta("workingDirectory", e.getWorkingDirectory());
//        LOGGER.error(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(CreateDirectoryException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleCreateDirectoryException(CreateDirectoryException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "Failed to create directory",
                e.getMessage()
        );
        info.addMeta("directory", e.getDirectory());
//        LOGGER.error(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(ZipEntryDirectoryException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleZipEntryDirectoryException(ZipEntryDirectoryException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "Zip entry has not correct directory",
                e.getMessage()
        );
        info.addMeta("directory", e.getDirectory());
        info.addMeta("name", e.getName());
//        LOGGER.error(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(UnzipException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleUnzipException(UnzipException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "File unzip failed",
                e.getMessage()
        );
        info.addMeta("workingDirectory", e.getWorkingDirectory());
//        LOGGER.error(String.format("error=%s", e.getMessage()));
        return info;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(ParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResource handleParseException(ParseException e) {
        ErrorResource info = new ErrorResource(
                e.getErrorCode(),
                "Parse failed",
                e.getMessage()
        );
//        LOGGER.info(String.format("error=%s", e.getMessage()));
        return info;
    }
}
