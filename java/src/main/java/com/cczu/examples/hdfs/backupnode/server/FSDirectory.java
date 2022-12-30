package com.cczu.examples.hdfs.backupnode.server;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 负责管理内存中的文件目录数的核心组件
 *
 * @author yjz
 * @date 2021/12/30
 */
public class FSDirectory {
    /**
     * 内存中的文件目录树
     */
    private final INodeDirectory dirTree;
    /**
     * 文件目录数的读写锁
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 当前文件目录数更新到了哪个txid对应的editslog
     */
    private long maxTxid = 0L;

    public void writeLock() {
        readWriteLock.writeLock().lock();
    }

    public void writeUnlock() {
        readWriteLock.writeLock().unlock();
    }

    public void readLock() {
        readWriteLock.readLock().lock();
    }

    public void readUnlock() {
        readWriteLock.readLock().unlock();
    }

    public FSDirectory() {
        // 根目录
        this.dirTree = new INodeDirectory("/");
    }

    /**
     * 获取文件目录树 大json
     *
     * @return
     */
    public FsImage getFsImage() {
        FsImage fsImage = null;
        try {
            readLock();
            String fsImageJson = JSON.toJSONString(dirTree);
            fsImage = new FsImage(maxTxid, fsImageJson);
        } finally {
            readUnlock();
        }
        return fsImage;
    }


    /**
     * 创建目录
     *
     * @param txid
     * @param path 文件路径
     */
    public void mkdir(long txid, String path) {
        // path = /usr/warehouse/hive
        // 内存数据结构更新时必须加锁
        try {
            writeLock();
            maxTxid = txid;
            String[] paths = path.split("/");
            INodeDirectory parent = dirTree;
            for (String splitedPath : paths) {
                if (splitedPath.trim().equals("")) {
                    continue;
                }
                INodeDirectory dir = findDirectory(parent, splitedPath);
                if (dir != null) {
                    parent = dir;
                    continue;
                }
                INodeDirectory child = new INodeDirectory(splitedPath);
                parent.addChild(child);
                parent = child;
            }
        } finally {
            writeUnlock();
        }
        System.out.println("当前文件目录数结构：" + dirTree);
    }

    /**
     * 对文件目录树查找,查找子目录
     *
     * @param dir
     * @param path
     * @return
     */
    public INodeDirectory findDirectory(INodeDirectory dir, String path) {
        if (dir.getChildren().size() == 0) {
            return null;
        }
        for (INode child : dir.getChildren()) {
            if (child instanceof INodeDirectory) {
                INodeDirectory childDir = (INodeDirectory) child;
                if ((childDir.getPath().equals(path))) {
                    return childDir;
                }
            }
        }
        return null;
    }

    /**
     * 代表文件目录中的一个节点
     */
    private interface INode {

    }

    /**
     * 代表文件目录数的一个目录
     */
    public static class INodeDirectory implements INode {
        private String path;
        private List<INode> children;

        public INodeDirectory(String path) {
            this.path = path;
            this.children = new LinkedList<>();
        }

        public void addChild(INode iNode) {
            this.children.add(iNode);
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<INode> getChildren() {
            return children;
        }

        public void setChildren(List<INode> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "INodeDirectory{" +
                    "path='" + path + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    /**
     * 代表文件目录数的一个文件
     */
    private class INodeFile implements INode {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "INodeFile{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


}
