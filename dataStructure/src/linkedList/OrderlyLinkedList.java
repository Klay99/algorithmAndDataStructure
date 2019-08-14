package linkedList;

/**
 * 有序链表
 */
public class OrderlyLinkedList<E extends Comparable<E> > {
    private Node head;// 头结点

    private class Node {
        private E data;
        private Node next;

        public Node() {
            data = null;
            next = null;
        }

        public Node(E data){
            this.data = data;
            next = null;
        }
    }

    public OrderlyLinkedList() {
        head = null;
    }

    public boolean isEmpty(){
        return (head == null);
    }

    // 插入结点，按按关键值从小到大排序，链表头关键值最小，从而实现有序链表
    public void add(E data){
        Node newNode = new Node(data);
        Node previous = null;
        Node current = head;
        // 遍历链表，找到要插入的位置，比插入值小时向后移动当前结点
        while (current != null && current.data.compareTo(data) < 0) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {// 插入的位置为链表头
            newNode.next = head;
            head = newNode;
        }else {
            // 循环结束，找到了插入位置，将前一结点指向新结点，将新结点指向当前结点
            previous.next = newNode;
            newNode.next = current;
        }
    }

    // 按关键值删除
    public void delete(E data){
        Node previous = null;
        Node current = head;
        if (isEmpty()) {
            throw new IllegalArgumentException("空链表无法删除");
        }
        // 遍历链表，找到要删除的元素，找到后循环结束
        while (current != null && !current.data.equals(data)) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {// 要删除的是头结点
            head = head.next;
        }else if(current != null) {// 链表中有要删除的元素
            // 循环结束，找到了要删除的元素，将前一结点指向下一结点，致空当前结点
            previous.next = current.next;
            current = null;
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
        OrderlyLinkedList oList = new OrderlyLinkedList();

        oList.add(13);
        oList.display();

        oList.add(4);
        oList.display();

        oList.delete(2);
        oList.display();

        oList.add(12);
        oList.display();

        oList.add(11);
        oList.display();

        oList.delete(10);
        oList.display();



    }
}
