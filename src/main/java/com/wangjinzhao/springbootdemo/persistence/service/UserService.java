package com.wangjinzhao.springbootdemo.persistence.service;

import com.wangjinzhao.springbootdemo.persistence.dao.UserRepository;
import com.wangjinzhao.springbootdemo.persistence.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WANGJINZHAO on 2018/1/26.
 */
public interface UserService {

    User save(User user);

    List<User> findByUserName(String userName);

    Page<User> listByPage(int pageNumber, int pageSize);

    Page<User> listDeletedByPage(int pageNumber, int pageSize, boolean deleted);

    User findOneByUserNameAndAK(String userName,String ak);

}
