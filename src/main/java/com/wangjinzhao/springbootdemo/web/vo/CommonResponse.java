package com.wangjinzhao.springbootdemo.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * Created by WANGJINZHAO on 2018/1/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse<T> implements Serializable {
    private String code;
    private String message;
    private int httpStatusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public CommonResponse(String code, String message, int httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
