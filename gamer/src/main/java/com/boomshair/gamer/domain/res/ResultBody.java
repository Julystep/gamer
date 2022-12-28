package com.boomshair.gamer.domain.res;

import com.boomshair.gamer.constant.ReturnCodeConstant;
import lombok.Getter;
import lombok.Setter;

/**
 * @Date: 2022/5/9
 * @Description:
 */
@Getter
@Setter
public class ResultBody {

    // 操作结果
    private Integer code;

    // 操作结果文字描述
    private String message;

    // 具体查询返回的结果
    private Object data;

    // 分页查询时的总量返回
    private Long total;

    public ResultBody(Integer code, String message) {
        this.code = code;
        this.message = message;
        data = null;
    }

    public ResultBody(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBody(Integer code, String message, Object data, Long total) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public static ResultBody error(Integer code, String message) {
        return new ResultBody(code, message);
    }

    public static ResultBody error(String message) {
        return new ResultBody(ReturnCodeConstant.ERROR, message);
    }

    public static ResultBody successWithData(Object data) {
        return success("请求成功", data);
    }

    public static ResultBody success(String message) {
        return success(message, null);
    }

    public static ResultBody success(String message, Object data) {
        return new ResultBody(ReturnCodeConstant.SUCCESS
                , message
                , data);
    }

    public static ResultBody success(Long total, Object data) {
        return success("请求成功", total, data);
    }

    public static ResultBody success(String message, Long total, Object data) {
        return new ResultBody(ReturnCodeConstant.SUCCESS
                , message
                , data
                , total);
    }
}
