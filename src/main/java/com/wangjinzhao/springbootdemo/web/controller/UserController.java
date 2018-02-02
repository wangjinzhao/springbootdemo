package com.wangjinzhao.springbootdemo.web.controller;


import com.wangjinzhao.springbootdemo.persistence.domain.User;
import com.wangjinzhao.springbootdemo.persistence.service.UserService;
import com.wangjinzhao.springbootdemo.util.ResponseUtil;
import com.wangjinzhao.springbootdemo.web.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by WANGJINZHAO on 2018/1/17.
 */
@Controller
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public
    @ResponseBody
    CommonResponse<User> addUser(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "userName") String userName,
                                 @RequestParam(value = "accesskey") String accesskey,
                                 @RequestParam(value = "accesskeySecret") String accesskeySecret,
                                 @RequestParam(value = "enable") boolean enable,
                                 @RequestParam(value = "deleted") boolean deleted) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);

        User user = new User();
        user.setAccesskey(accesskey);
        user.setAccesskeySecret(accesskeySecret);
        user.setUserName(userName);
        user.setEnable(true);
        user.setDeleted(false);
        try {
            user = userService.save(user);
            commonResponse.setResult(user);
        } catch (Exception e) {
            commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;
    }

    @RequestMapping(value = "/putUser", method = RequestMethod.PUT)
    public
    @ResponseBody
    CommonResponse<User> putUser(HttpServletRequest httpServletRequest, HttpServletResponse response, @RequestBody User user) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);

        User result = null;
        try {
            result = userService.save(user);
            commonResponse.setResult(user);
        } catch (Exception e) {
            return ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;
    }

    @RequestMapping(value = "/getUser/{userName}", method = RequestMethod.GET)
    public
    @ResponseBody
    CommonResponse<List<User>> getUser(final HttpServletRequest request, @PathVariable String userName) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);
        try {
            commonResponse.setResult(userService.findByUserName(userName));
        } catch (Exception e) {

            e.printStackTrace();
            commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;
    }

    /**
     * 带有userName参数才会走
     */
    @RequestMapping(value = "/getUserByParam", method = RequestMethod.GET, params = "userName")
    public
    @ResponseBody
    CommonResponse<List<User>> getUserByUserName(HttpServletRequest request, @RequestParam(value = "userName") String userName) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);
        try {
            List<User> result = userService.findByUserName(userName);
            commonResponse.setResult(result);
        } catch (Exception e) {
            commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;
    }


    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public
    @ResponseBody
    CommonResponse<Page<User>> listAll(@RequestParam("pageNumber") int pageNumber,
                                       @RequestParam("pageSize") int pageSize) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);
        try {
            Page<User> result = userService.listByPage(pageNumber - 1, pageSize);
            commonResponse.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;

    }

    @RequestMapping(value = "/listAllDeleted", method = RequestMethod.GET)
    public
    @ResponseBody
    CommonResponse<Page<User>> listAllActive(@RequestParam("pageNumber") int pageNumber,
                                             @RequestParam("pageSize") int pageSize,
                                             @RequestParam("deleted") boolean deleted) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);
        try {
            Page<User> result = userService.listDeletedByPage(pageNumber - 1, pageSize, deleted);
            commonResponse.setResult(result);
        } catch (Exception e) {
            commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    public
    @ResponseBody
    CommonResponse<User> findOne(@RequestParam("userName") String userName,
                                 @RequestParam("accessKey") String accessKey) {
        CommonResponse commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.OK);
        try {
            User result = userService.findOneByUserNameAndAK(userName, accessKey);
            commonResponse.setResult(result);
        } catch (Exception e) {
            commonResponse = ResponseUtil.createCommonResponse(ResponseUtil.APIResponseEnum.InternalError);
        }
        return commonResponse;
    }

}
