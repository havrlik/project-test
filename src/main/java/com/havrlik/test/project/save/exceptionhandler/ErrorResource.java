package com.havrlik.test.project.save.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResource {

    private final String id;
    private final ErrorCode code;
    private final String title;
    private final String details;
    private Map<String, Object> meta;

    public ErrorResource(
            ErrorCode code,
            String title,
            String details
    ) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.title = title;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void addMeta(final String key, final Object value) {
        if (this.meta == null) {
            this.meta = new HashMap<>();
        }

        Object val = value;
        if (val == null) {
            val = "<null>";
        }

        this.meta.put(key, val);
    }
}
