package com.boomshair.gamer.config;

import com.boomshair.gamer.domain.res.ResultBody;
import com.boomshair.gamer.exception.GameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @since v1.0.0
 * @since 2022/12/27
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = Exception.class)
    public ResultBody velocityException(Exception e){
        if (e instanceof GameException) {
            return ResultBody.error(e.getMessage());
        } else {
            log.error("系统异常", e);
            return ResultBody.error("系统异常");
        }
    }

}
