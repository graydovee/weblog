package cn.graydove.blog.exceptions;

import cn.graydove.blog.enums.ServerStatus;

public abstract class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract ServerStatus getStatus();
}
