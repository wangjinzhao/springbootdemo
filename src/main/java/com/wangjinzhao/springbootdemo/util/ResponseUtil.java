package com.wangjinzhao.springbootdemo.util;

import com.wangjinzhao.springbootdemo.web.vo.CommonResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by WANGJINZHAO on 2018/1/25.
 */
public class ResponseUtil {

    @Getter
    public static enum APIResponseEnum {

        DisableUser("DisableUser", "The User is disable, login is refuse!", HttpStatus.FORBIDDEN),
        InvalidArgument("InvalidArgument", "Invalid Argument", HttpStatus.BAD_REQUEST),
        OK("OK", "ok", HttpStatus.OK),
        InternalError("InternalError", "We encountered an internal error. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);


        private String code;
        private String msg;
        private HttpStatus httpStatus;

        APIResponseEnum(String code, String msg, HttpStatus httpStatus) {
            this.code = code;
            this.msg = msg;
            this.httpStatus = httpStatus;
        }


    }

    public static CommonResponse createCommonResponse(APIResponseEnum error) {
        CommonResponse response = new CommonResponse(error.code, error.msg, error.httpStatus.value());
        return response;
    }


}
