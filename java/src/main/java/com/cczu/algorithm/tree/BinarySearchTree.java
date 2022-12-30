package com.cczu.algorithm.tree;

/**
 * 二叉搜索树
 *
 * @author jianzhen.yin
 * @date 2020/12/7
 */
public class BinarySearchTree {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node tree;

    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (true) {
            if (p.data > data) {
                if (p.left == null) {
                    p.left = new Node(data);
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(data);
                    break;
                }
                p = p.right;
            }
        }
    }

    public int find(int data) {
        if (tree == null) {
            return -1;
        }
        Node p = tree;
        while (p != null) {
            if (p.data == data) {
                return p.data;
            } else if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return -1;
    }

    public void delete(int data) {
        // 指向要删除的节点
        Node p = tree;
        // 指向要删除节点的父节点
        Node pp = null;
        if (p != null && p.data != data) {
            pp = p;
            if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        // 无数据
        if (p == null) {
            return;
        }
        // 1.存在左右子节点
        if (p.left != null && p.right != null) {
            // 找到最左的节点
            Node minP = p.right;
            Node minPP = null;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 将要删除的节点的值换成其右子树最小的值
            p.data = minP.data;
            // 现在切换成删除minP节点了
            p = minP;
            pp = minPP;
        }
        // 2.删除节点是叶子节点或只有一个节点
        // p的子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }
        // 删除的是跟节点
        if (pp == null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    public void print() {
        Node p = this.tree;
        System.out.println("前序遍历");
        preOrder(p);
        System.out.println();
        System.out.println("中序遍历");
        inOrder(p);
        System.out.println();
        System.out.println("后序遍历");
        postOrder(p);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + "|");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(String.valueOf(node.data)+"|");
        inOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + "|");
    }

    /**
     * 为什么有了散列表还需要二叉树？
     * 我们在散列表那节中讲过，散列表的插入、删除、查找操作的时间复杂度可以做到常量级的 O(1)，非常高效。
     * 而二叉查找树在比较平衡的情况下，插入、删除、查找操作时间复杂度才是 O(logn)，相对散列表，好像并没有什么优势，那我们为什么还要用二叉查找树呢？
     * 我认为有下面几个原因：
     * 第一，散列表中的数据是无序存储的，如果要输出有序的数据，需要先进行排序。而对于二叉查找树来说，我们只需要中序遍历，就可以在 O(n) 的时间复杂度内，输出有序的数据序列。
     * 第二，散列表扩容耗时很多，而且当遇到散列冲突时，性能不稳定，尽管二叉查找树的性能不稳定，但是在工程中，我们最常用的平衡二叉查找树的性能非常稳定，时间复杂度稳定在 O(logn)。
     * 第三，笼统地来说，尽管散列表的查找等操作的时间复杂度是常量级的，但因为哈希冲突的存在，这个常量不一定比 logn 小，所以实际的查找速度可能不一定比 O(logn) 快。加上哈希函数的耗时，也不一定就比平衡二叉查找树的效率高。
     * 第四，散列表的构造比二叉查找树要复杂，需要考虑的东西很多。比如散列函数的设计、冲突解决办法、扩容、缩容等。
     * 平衡二叉查找树只需要考虑平衡性这一个问题，而且这个问题的解决方案比较成熟、固定。最后，为了避免过多的散列冲突，散列表装载因子不能太大，特别是基于开放寻址法解决冲突的散列表，不然会浪费一定的存储空间。
     *
     * @param args
     */
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(3);
        binarySearchTree.insert(1);
        binarySearchTree.insert(4);
        binarySearchTree.insert(5);
        binarySearchTree.insert(6);
        binarySearchTree.insert(7);
        binarySearchTree.insert(8);

        binarySearchTree.print();
        System.out.println();
        System.out.println("search "+binarySearchTree.find(1));
        binarySearchTree.delete(3);
        binarySearchTree.print();
    }
}
