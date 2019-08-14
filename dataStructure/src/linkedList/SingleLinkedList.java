package linkedList;

/**
 * 单向链表
 */
public class SingleLinkedList<E> {

    // 结点
    private Node head;

    // 链表长度
    private int size;

    private class Node {

        // 结点存储的数据
        private E data;
        // 指向下一结点的指针
        private Node next;

        // 默认数据为空，指针指向空
        public Node() {
            data = null;
            next = null;
        }

        // 引用结点时加入数据，指针指向空
        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    public SingleLinkedList() {
        head = null;
        size = 0;
    }

    private int getSize() {
        return size;
    }

    // 在链表头添加
    private void add(E e) {
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    private void remove(E e) {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }

        Node current = head;
        Node previous = head;
        if (head.data.equals(e)) {// 头结点为要删除的结点，将头结点设为下一个结点，并将原头结点致空
            head = head.next;
            size--;
        } else {
            // 当前结点为空结束循环，没找到时后移，找到后
            while (current != null) {
                if (!current.data.equals(e)) {
                    previous = current;
                    current = current.next;
                } else {
                    previous.next = current.next;
                    current.next = null;
                    size--;
                    break;
                }
            }
        }
    }

    private boolean isEmpty() {
        return head == null;
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
        SingleLinkedList<Integer> sList = new SingleLinkedList<>();

        sList.add(1);
        sList.remove(12);
        sList.display();// 1

        sList.add(1);
        sList.remove(1);
        sList.display();// 1

        sList.add(1);
        sList.add(3);
        sList.remove(1);
        sList.display();// 3 1
        //3 1
        sList.add(1);
        sList.add(3);
        sList.remove(4);
        sList.display();// 3 1 3 1
        // 3 1 3 1
        sList.add(1);
        sList.add(3);
        sList.remove(3);
        sList.display();// 1 3 1 3 1

        sList.remove(0);
        sList.display();

        sList.remove(1);
        sList.display();
        sList.remove(1);
        sList.display();
        System.out.println("!11");

        sList.remove(1);
        sList.display();
        sList.remove(1);
        sList.display();

        sList.remove(3);
        sList.display();
        sList.remove(3);
        sList.display();

    }
}
