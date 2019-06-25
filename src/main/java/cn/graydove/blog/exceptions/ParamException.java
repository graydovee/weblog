package cn.graydove.blog.exceptions;

import cn.graydove.blog.enums.ServerStatus;

public class ParamException extends BaseException {


    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ServerStatus getStatus() {
        return ServerStatus.PARAM_ERROR;
    }
}
