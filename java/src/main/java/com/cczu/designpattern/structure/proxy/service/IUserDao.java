package com.cczu.designpattern.structure.proxy.service;

import com.cczu.designpattern.structure.proxy.annotation.Select;

public interface IUserDao {
    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);
}
