package com.xxxx.hrm.query;

import com.xxxx.hrm.base.BaseQuery;


//看情况使用（王龙）
public class EmployeeQuery extends BaseQuery {

    private String name;

    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
