package com.cczu.designpattern.behavior.iterator;

import com.cczu.designpattern.behavior.iterator.lang.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        GroupStructure groupStructure = new GroupStructure("1", "⼩小傅哥");
// 雇员信息
        groupStructure.add(new Employee("2", "花花", "⼆二级部⻔门"));
        groupStructure.add(new Employee("3", "⾖豆包", "⼆二级部⻔门"));
        groupStructure.add(new Employee("4", "蹦蹦", "三级部⻔门"));
        groupStructure.add(new Employee("5", "⼤大烧", "三级部⻔门"));
        groupStructure.add(new Employee("6", "⻁虎哥", "四级部⻔门"));
        groupStructure.add(new Employee("7", "玲姐", "四级部⻔门"));
        groupStructure.add(new Employee("8", "秋雅", "四级部⻔门"));
// 节点关系 1->(1,2) 2->(4,5)
        groupStructure.addLink("1", new Link("1", "2"));
        groupStructure.addLink("1", new Link("1", "3"));
        groupStructure.addLink("2", new Link("2", "4"));
        groupStructure.addLink("2", new Link("2", "5"));
        groupStructure.addLink("5", new Link("5", "6"));
        groupStructure.addLink("5", new Link("5", "7"));
        groupStructure.addLink("5", new Link("5", "8"));
        Iterator<Employee> iterator = groupStructure.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            logger.info("{}，雇员 Id:{} Name:{}", employee.getDesc(), employee.getuId(), employee.getName());
        }
    }

}
