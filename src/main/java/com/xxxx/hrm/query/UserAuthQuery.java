package com.xxxx.hrm.query;

import com.xxxx.hrm.base.BaseQuery;

//权限管理多条件查询条件
public class UserAuthQuery extends BaseQuery {
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}