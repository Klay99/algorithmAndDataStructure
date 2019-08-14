package linkedList;

/**
 * 双向有序链表
 */
public class DoublyLinkedList<E extends Comparable<E> > {

    // 头结点
    private Node head;

    // 尾结点
    private Node tail;

    private class Node {

        // 结点存储的数据
        private E data;
        // 指向下一结点的指针
        private Node next;
        // 指向上一结点的指针
        private Node previous;

        // 默认数据为空，指针指向空
        public Node() {
            data = null;
            next = null;
            previous = null;
        }

        // 引用结点时加入数据，指针指向空
        public Node(E data) {
            this.data = data;
            next = null;
            previous = null;
        }
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty(){
        return (head == null);
    }

    // 在链表头添加
    public void addHead(E e){
        Node newHead = new Node(e);
        if (isEmpty()) {
            tail = newHead;
        }else {
            newHead.next = head;
            head.previous = newHead;
        }
        head = newHead;
    }

    // 在链表尾添加
    public void addTail(E e){
        Node newTail = new Node(e);
        if (isEmpty()) {
            head = newTail;
        }else {
            tail.next = newTail;
            newTail.previous = tail;
        }
        tail = newTail;
    }

    // 在链表头删除
    public Node deleteHead(){
        Node temp = head;
        head = head.next;
        head.previous = null;
        if (head == null) {
            tail = null;
        }
        return temp;
    }

    // 在链表尾删除
    public Node deleteTail(){
        Node temp = tail;
        tail = tail.previous;
        tail.next = null;
        if (tail == null) {
            head = null;
        }
        return temp;
    }

    // 按值删除指定结点
    public void delete(E e){
        Node current = head;// 从头结点开始遍历
        while (current != null) {
            if (!current.data.equals(e)){
                current = current.next;
            }else {
                if (current == head) {
                    deleteHead();
                }else if (current == tail) {
                    deleteTail();
                }else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    current.previous = null;
                    current.next = null;
                }
                break;// 删一个跳出，删多个不用跳出
            }
        }
    }

    // 插入结点，并按关键值有序排列，实现有序链表
    private void addOrdered(E e){
        Node newNode = new Node(e);
        Node current = head;
        // 当前结点不为空，且当前结点值小于插入值
        while (current != null && current.data.compareTo(e) < 0) {
            current = current.next;
        }
        if (current == head) {
            addHead(e);
        }else if (current == null) {
            addTail(e);
        }else {// 当跳出循环时，当前结点的位置就是插入结点的位置
            current.previous.next = newNode;
            newNode.previous = current.previous;
            newNode.next = current;
            current.previous = newNode;
        }
    }

    public void display(){
        Node current = head;
        while (current != null) {
            System.out.print(current.data + "\t");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> dList = new DoublyLinkedList();

        dList.addHead(10);
        dList.display();

        dList.addTail(13);
        dList.display();

        dList.addOrdered(12);
        dList.display();

        dList.addOrdered(7);
        dList.addOrdered(11);
        dList.addOrdered(15);
        dList.display();

        dList.delete(11);
        dList.display();

        dList.deleteHead();
        dList.display();

        dList.deleteTail();
        dList.display();
    }

}
