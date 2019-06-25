package cn.graydove.blog.exceptions;

import cn.graydove.blog.enums.ServerStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ServerStatus getStatus() {
        return ServerStatus.RESOURCE_NOT_FOUND;
    }
}
