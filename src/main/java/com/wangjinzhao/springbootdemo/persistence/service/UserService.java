package com.wangjinzhao.springbootdemo.persistence.service;

import com.wangjinzhao.springbootdemo.persistence.domain.User;
import org.springframework.data.domain.Page;


import java.util.List;

/**
 * Created by WANGJINZHAO on 2018/1/26.
 */
public interface UserService {

    User save(User user);

    List<User> findByUserName(String userName);

    Page<User> listByPage(int pageNumber, int pageSize);

    Page<User> listDeletedByPage(int pageNumber, int pageSize, boolean deleted);

    Page<User> listComplexByPage(String accesskey, String accesskeySecret, String userName, int pageNum, int pageSize);

    //Page<User> listExampleByPage(String accesskey, String accesskeySecret, String userName, int pageNum, int pageSize);

    User findOneByUserNameAndAK(String userName, String ak);


}
