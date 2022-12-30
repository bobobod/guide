package com.cczu.designpattern.behavior.mediator.dao;


import com.cczu.designpattern.behavior.mediator.po.School;

public interface ISchoolDao {

    School querySchoolInfoById(Long treeId);

}
