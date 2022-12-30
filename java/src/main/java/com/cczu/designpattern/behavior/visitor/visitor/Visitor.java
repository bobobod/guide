package com.cczu.designpattern.behavior.visitor.visitor;


import com.cczu.designpattern.behavior.visitor.user.impl.Student;
import com.cczu.designpattern.behavior.visitor.user.impl.Teacher;

public interface Visitor {

    // 访问学生信息
    void visit(Student student);

    // 访问老师信息
    void visit(Teacher teacher);

}
