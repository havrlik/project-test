package com.havrlik.test.project.save.exceptionhandler;

public enum ErrorCode {

    ENTITY_NOT_UNIQUE("entity-not-unique"),
    FILE_DELETE_FAIL("file-delete-fail"),
    FILE_DOWNLOAD_FAIL("file-download-fail"),
    EMPTY_FILE("empty-file"),
    DIRECTORY_CREATE_FAIL("directory-create-fail"),
    ZIP_ENTRY_NOT_CORRECT_DIRECTORY("zip-entry-not-correct-directory"),
    UNZIP_FAIL("unzip-fail"),
    PARSE_FAIL("parse-fail"),
    ;

    public final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
