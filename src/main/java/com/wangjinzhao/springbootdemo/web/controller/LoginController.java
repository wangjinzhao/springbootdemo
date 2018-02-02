package com.wangjinzhao.springbootdemo.web.controller;

import com.wangjinzhao.springbootdemo.persistence.domain.User;
import com.wangjinzhao.springbootdemo.util.ResponseUtil;
import com.wangjinzhao.springbootdemo.web.vo.CommonResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by WANGJINZHAO on 2018/1/17.
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public CommonResponse<User> login(HttpServletRequest httpServletRequest, HttpServletResponse response, @PathVariable String userName) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);
        User user = new User();
        user.setUserName(userName);
        user.setId(1L);
        commonResponse.setResult(user);
        if (user.getUserName().endsWith("wangjinzhao")) {
            ResponseUtil.APIResponseEnum apiResponseEnum = ResponseUtil.APIResponseEnum.InvalidArgument;
            response.setStatus(apiResponseEnum.getHttpStatus().value());
            return commonResponse = ResponseUtil.createCommonResponse(apiResponseEnum);
        }
        return commonResponse;
    }

}
