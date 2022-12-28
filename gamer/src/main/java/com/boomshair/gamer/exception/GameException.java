package com.boomshair.gamer.exception;

/**
 * 自定义异常类
 * @since v1.0.0
 * @since 2022/12/27
 */
public class GameException extends RuntimeException {

    private final Integer code;

    private final String message;

    public GameException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public GameException(String message) {
        super(message);
        this.code = -1;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
