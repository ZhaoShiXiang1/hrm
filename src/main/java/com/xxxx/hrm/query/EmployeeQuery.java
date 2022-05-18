package com.xxxx.hrm.query;

import com.xxxx.hrm.base.BaseQuery;


//看情况使用（王龙）
public class EmployeeQuery extends BaseQuery {

    private String name;

    private String phone;

    private String idcard;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

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
