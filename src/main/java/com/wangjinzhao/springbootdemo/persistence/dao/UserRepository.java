package com.wangjinzhao.springbootdemo.persistence.dao;

import com.wangjinzhao.springbootdemo.persistence.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by WANGJINZHAO on 2018/1/26.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserNameAndDeleted(String userName, Boolean deleted);

    Page<User> findByDeleted(boolean deleted, Pageable pageRequest);

    @Query(value = "select * from user where user_name=:userName and accesskey=:accesskey limit 1", nativeQuery = true)
    public User findOneByUserNameAndAK(@Param("userName") String bucket_name, @Param("accesskey") String accesskey);


}
