package com.wangjinzhao.springbootdemo.persistence.service.impl;

import com.wangjinzhao.springbootdemo.persistence.dao.UserRepository;
import com.wangjinzhao.springbootdemo.persistence.domain.User;
import com.wangjinzhao.springbootdemo.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
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


    @Override
    public Page<User> listComplexByPage(String accesskey, String accesskeySecret, String userName, int pageNum, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        //Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<User> userPage = this.userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> userNamePath = root.get("userName");
                Path<Integer> akPath = root.get("accesskey");
                Path<Integer> skPath = root.get("accesskeySecret");
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(userName)) {
                    //predicateList.add(criteriaBuilder.equal(userNamePath.as(String.class), userName));
                    predicateList.add(criteriaBuilder.like(userNamePath.as(String.class), "%" + userName + "%"));
                }
                if (!StringUtils.isEmpty(accesskey)) {
                    predicateList.add(criteriaBuilder.equal(akPath.as(String.class), accesskey));
                }
                if (!StringUtils.isEmpty(accesskeySecret)) {
                    predicateList.add(criteriaBuilder.equal(skPath.as(String.class), accesskeySecret));
                }
//                if (null != age && age > 0) {
//                    predicateList.add(criteriaBuilder.equal(agePath.as(Integer.class), age));
//                }
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        }, pageable);
        return userPage;
    }


//    public Page<User> listExampleByPage(String accesskey, String accesskeySecret, String userName, int pageNum, int pageSize) {
//        List<User> userList = null;
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
//
//        //创建查询条件数据对象
//        User user = new User();
//        user.setAccesskeySecret(accesskeySecret);
//        user.setAccesskey(accesskey);
//        user.setUserName(userName);
//
//        //创建匹配器，即如何使用查询条件
//        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//                .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.exact());  //姓名采用“完全匹配，大小写敏感”的方式查询
//        Example<User> example = Example.of(user, matcher);
//        Page<User> userPage = this.userRepository.findAll(example, pageable);
//        return userPage;
//    }


}
