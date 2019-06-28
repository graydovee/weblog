package cn.graydove.weblog.enums;

import lombok.Getter;

@Getter
public enum ServerStatus {
    OK(200,"OK"),
    RESOURCE_NOT_FOUND(400,"resource not found"),
    FORBIDDEN(403,"you don't have permission to do it"),
    SERVER_ERROR(500,"server error"),
    PARAM_ERROR(501,"param error"),
    NULL_PARAM(502,"param is null");

    private int value;
    private String reason;

    ServerStatus(int value) {
        this(value,null);
    }

    ServerStatus(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
