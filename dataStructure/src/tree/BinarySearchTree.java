package tree;

/**
 * 二叉树（二叉搜索树）
 */
public class BinarySearchTree<E extends Comparable<E> > {
    private Node root;
    private int size;

    private class Node {
        private Node left;
        private Node right;
        private E data;

        public Node() {
            left = null;
            right = null;
        }

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加
    public void add(E e){
        Node newNode = new Node(e);
        if (isEmpty()) {
            root = newNode;
        }else {
            Node parent = null;// 父结点
            Node current = root;
            while (current != null) {
                parent = current;// 更新父结点
                if (e.compareTo(current.data) < 0){// 插入值小于当前值，往左更新当前结点
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                    }
                }else {// 往右更新当前结点
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                    }
                }
            }
        }
        size++;
        display();
    }

    // 删除，先遍历树找到要删除的目标结点，再分情况进行删除操作
    public boolean delete(E key){
        // 找到目标结点

        Node parent = root;// 当前结点的父结点
        Node current = root;
        boolean isLeft = false;// 用于判断当前结点是否为其父结点的左结点
        while (!current.data.equals(key)) {// 找到要删除的结点，跳出循环
            parent = current;
            if (key.compareTo(current.data) < 0) {// 要删除的值小于当前结点的值，将当前结点左移
                current = current.left;
                isLeft = true;
            }else {// 要删除的值不小于当前结点的值，将当前结点右移
                current = current.right;
                isLeft = false;
            }
            // 遍历结束，没找到要删除的元素，删除失败
            if (current == null) {
                return false;
            }
        }

        // 循环结束，找到了目标结点，按情况进行删除操作

        /**
         * 1.目标结点没有子结点，及删除的结点为叶子结点，直接将其父结点的左（或右）指向空
         */
        if (current.left == null && current.right == null){
            if (current == root) {// 根结点为要删除的结点
                root = null;
            }else if (isLeft) {// 将父结点的左指针指向空
                parent.left = null;
            }else {
                parent.right = null;
            }
            size--;
            display();// 删除成功，打印验证
            return true;
        }

        /**
         * 2.目标结点有一个子结点，直接将其父结点的左（或右）指向目标结点的子结点
         */
        if (current.left == null && current.right != null){// 当前（目标）结点有一个右子结点
            if (current == root) {// 当前结点为根结点，将根结点设为其右子结点
                root = current.right;
            }else if (isLeft) {// 将其父结点的左指针指向当前结点的右子结点
                parent.left = current.right;
            }else {// 将其父结点的右指针指向当前结点的右子结点
                parent.right = current.right;
            }
            size--;
            display();// 删除成功，打印验证
            return true;
        }else if (current.right == null && current.left != null){// 当前（目标）结点有一个左子结点
            if (current == root) {
                root = current.left;
            }else if (isLeft) {
                parent.left = current.left;
            }else {
                parent.right = current.left;
            }
            size--;
            display();// 删除成功，打印验证
            return true;
        }

        /**
         * 3.目标结点有两个子结点，找到后继结点并替换
         * 先删掉目标结点；在被删除结点的左子树中寻找最大结点；将最大结点移到被删除结点的位置上
         */
        if (current.left != null && current.right != null){
            Node successor = getSuccessor(current);// 拿到后继结点

            // 用后继结点替换目标结点
            if (current == root) {
                root = successor;
            }else if (isLeft) {
                parent.left = successor;
            }else {
                parent.right = successor;
            }
            size--;
            display();// 删除成功，打印验证
            return true;
        }
        return false;
    }

    /**
     * 寻找目标结点的后继结点，在目标结点的左子树中找到最大结点
     *
     * @param target 目标结点
     * @return successor 返回后继结点
     */
    public Node getSuccessor(Node target){
        Node successorParent = target;// 后继结点的父结点
        Node successor = target;// 后继结点
        Node current = target.left;// 当前结点从目标结点的左子结点开始
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.right;// 最大结点一定在最右边，当前结点右移
        }
        // 循环结束，找到了最大结点（后继结点）
        if (successor != target.left) {// 如果后继结点不是目标结点的左子结点
            successorParent.right = successor.left;
            successor.left = target.left;
        }
        // 如果后继结点为目标结点的左子结点
        successor.right = target.right;
        return successor;
    }


    /**
     * 递归实现树的遍历
     */

    // 打印，中序遍历（左、根、右）
    public void midOrderDisplay(Node current){
        if (current != null) {
            midOrderDisplay(current.left);
            System.out.print(current.data + "\t");
            midOrderDisplay(current.right);
        }
    }

    // 打印，前序遍历（根、左、右）
    public void preOrderDisplay(Node current){
        if (current != null) {
            System.out.print(current.data + "\t");
            preOrderDisplay(current.left);
            preOrderDisplay(current.right);
        }
    }

    // 打印，后序遍历（左、右、根）
    public void postOrderDisplay(Node current){
        if (current != null) {
            postOrderDisplay(current.left);
            postOrderDisplay(current.right);
            System.out.print(current.data + "\t");
        }
    }

    // 默认打印，后续遍历，从根结点开始
    public void display(){
        System.out.println("display by post order:left,right,root");
        postOrderDisplay(root);
        System.out.println("\n");
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

//        tree.add(15);
//        tree.postOrderDisplay(tree.root);
//        System.out.println();
//        // 15
//
//        tree.add(9);
//        tree.postOrderDisplay(tree.root);
//        System.out.println();// 9 15
//        tree.preOrderDisplay(tree.root);
//        System.out.println();
//        //
//
//        tree.add(23);
//        tree.postOrderDisplay(tree.root);
//        System.out.println();
//        tree.midOrderDisplay(tree.root);
//        System.out.println();
//
//        tree.add(3);
//        tree.midOrderDisplay(tree.root);
//        System.out.println();
//
//        tree.add(17);
//        tree.add(12);
//        tree.add(28);
//        tree.add(8);
//        tree.midOrderDisplay(tree.root);
//        System.out.println();
//        tree.preOrderDisplay(tree.root);
//        System.out.println();
//        tree.postOrderDisplay(tree.root);
//        System.out.println();// 8 3 12 9 17 28 23 15

        tree.add(15);   tree.add(23);   tree.add(9);    tree.add(12);   tree.add(17);   tree.add(3);
        tree.add(28);   tree.add(1);    tree.add(8);    tree.add(4);    tree.add(5);    tree.add(10);
    }

}
