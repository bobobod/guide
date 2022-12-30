package com.cczu.designpattern.behavior.iterator;

import com.cczu.designpattern.behavior.iterator.lang.Collection;
import com.cczu.designpattern.behavior.iterator.lang.Iterator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class GroupStructure implements Collection<Employee, Link> {
    /**
     * 组织ID，也是组织链的头部ID
     */
    private String groupId;
    /**
     * 组织名称
     */
    private String groupName;
    /**
     * 雇员列表
     */
    private Map<String, Employee> employeeMap = new ConcurrentHashMap<>();
    /**
     * 组织架构关系 id->list
     */
    private Map<String, List<Link>> linkMap = new ConcurrentHashMap<>();
    /**
     * 反向关系链
     */
    private Map<String, String> invertedMap = new ConcurrentHashMap<>();

    public GroupStructure(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Override
    public boolean add(Employee employee) {
        return null != employeeMap.put(employee.getuId(), employee);
    }

    @Override
    public boolean remove(Employee employee) {
        return null != employeeMap.remove(employee.getuId());
    }

    @Override
    public boolean addLink(String key, Link link) {
        invertedMap.put(link.getToId(), link.getFromId());
        if (linkMap.containsKey(key)) {
            return linkMap.get(key).add(link);
        } else {
            List<Link> links = new LinkedList<>();
            links.add(link);
            linkMap.put(key, links);
            return true;
        }
    }

    @Override
    public boolean removeLink(String key) {
        return null != linkMap.remove(key);
    }

    @Override
    public Iterator<Employee> iterator() {
        /**
         * 1. 这⾥里里的树形结构我们需要做的是深度遍历，也就是左侧的⼀一直遍历到最深节点。
         * 2. 当遍历到最深节点后，开始遍历最深节点的横向节点。
         * 3. 当横向节点遍历完成后则向上寻找横向节点，直⾄至树结构全部遍历完成。
         */
        return new Iterator<Employee>() {
            Map<String, Integer> keyMap = new HashMap<>();
            int totalIdx = 0;
            private String fromId = groupId;
            private String toId = groupId;

            @Override
            public boolean hasNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                List<Link> links = linkMap.get(toId);
                int cursorIdx = getCursorIdx(toId);
                // 同级节点扫描
                if (null == links) {
                    cursorIdx = getCursorIdx(fromId);
                    links = linkMap.get(fromId);
                }
                // 上级节点扫描
                while (cursorIdx > links.size() - 1) {
                    fromId = invertedMap.get(fromId);
                    cursorIdx = getCursorIdx(fromId);
                    links = linkMap.get(fromId);
                }
                // 获取节点
                Link link = links.get(cursorIdx);
                toId = link.getToId();
                fromId = link.getFromId();
                totalIdx++;
                // 返回结果
                return employeeMap.get(link.getToId());
            }

            // 给每个层级定义宽度遍历进度
            public int getCursorIdx(String key) {
                int idx = 0;
                if (keyMap.containsKey(key)) {
                    idx = keyMap.get(key);
                    keyMap.put(key, ++idx);
                } else {
                    keyMap.put(key, idx);
                }
                return idx;
            }

        };
    }
}
