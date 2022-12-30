package com.cczu.designpattern.behavior.mediator.dao;


import com.cczu.designpattern.behavior.mediator.po.User;

public interface IUserDao {

     User queryUserInfoById(Long id);

}
