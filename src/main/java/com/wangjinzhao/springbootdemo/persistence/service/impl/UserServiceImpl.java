package com.wangjinzhao.springbootdemo.persistence.service.impl;

import com.wangjinzhao.springbootdemo.persistence.dao.UserRepository;
import com.wangjinzhao.springbootdemo.persistence.domain.User;
import com.wangjinzhao.springbootdemo.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANGJINZHAO on 2018/1/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        user = userRepository.save(user);
        //new Integer("xxxx");
        return user;
    }

    @Override
    public List<User> findByUserName(String userName) {
        return userRepository.findByUserNameAndDeleted(userName, false);
    }

    @Override
    public Page<User> listByPage(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> listDeletedByPage(int pageNumber, int pageSize, boolean deleted) {
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return userRepository.findByDeleted(deleted, pageable);
    }

    @Override
    public User findOneByUserNameAndAK(String userName, String ak) {
        return userRepository.findOneByUserNameAndAK(userName, ak);
    }
}
