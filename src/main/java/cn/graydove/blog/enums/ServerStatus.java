package cn.graydove.blog.enums;

import lombok.Getter;

@Getter
public enum ServerStatus {
    OK(200,"OK"),
    RESOURCE_NOT_FOUND(400,"resource not found"),
    SERVER_ERROR(500,"server error"),
    PARAM_ERROR(501,"param error");

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
