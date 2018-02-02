package com.wangjinzhao.springbootdemo.persistence.domain;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import static java.lang.Boolean.FALSE;

/**
 * Created by WANGJINZHAO on 2018/1/17.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Entity
@javax.persistence.Table(name = "user",
        //uniqueConstraints = {@UniqueConstraint(name = "uniq_username_index", columnNames = "{user_name}")},
        indexes = {})
@javax.persistence.EntityListeners(AuditingEntityListener.class)
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic(optional = false)
    @Column(name = "user_name", nullable = false, updatable = false, length = 256)
    private String userName;

    @Size(min = 10, max = 20)
    @Basic(optional = false)
    @Column(name = "accesskey", nullable = false, updatable = false, length = 20)
    private String accesskey;


    @Size(min = 20, max = 70)
    @Basic(optional = false)
    @Column(name = "accesskey_secret", nullable = false, updatable = false, length = 128)
    private String accesskeySecret;

    @NotNull
    @Basic(optional = false)
    @Column(name = "enable", nullable = false, updatable = true)
    private Boolean enable = FALSE;

    @CreatedDate
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Basic(optional = false)
    @Column(name = "created_time", nullable = false, updatable = true)
    private DateTime createdTime = DateTime.now();

    @NotNull
    @Basic(optional = false)
    @Column(name = "deleted", nullable = false, updatable = true)
    private Boolean deleted = FALSE;

    @LastModifiedDate
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Basic(optional = false)
    @Column(name = "last_modified_time", nullable = false, updatable = true)
    private DateTime lastModifiedTime = DateTime.now();

}
