package linkedList;

/**
 * 循环链表，虚拟头结点实现
 */
public class CircleLinkedList<E> {
    // 虚拟头结点，头结点不存数据，从头结点的下一个开始存
    private Node head;
    // 链表长度
    private int size;

    private class Node {
        // 存储数据
        private E data;
        // 指向下一结点的指针
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

    // 默认链表为空，因为是循环链表，所以头结点指向头结点
    public CircleLinkedList() {
        head = new Node();// 由于有虚拟头结点，所以先要new一个结点，并将其指向自身
        head.data = null;
        head.next = head;
        size = 0;
    }

    // 按索引添加结点
    public void add(int index,E e){
        Node newNode = new Node(e);
        Node current = head;
        size++;// 为空时在虚拟头结点的下一个添加，所以先将size加一
        if (index <= 0 || index > size) {
            size--;
            System.out.println("下标错误");
        }else {
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // 在链表头部（虚拟头结点的下一个结点）添加
    public void addHead(E e){
//        Node newNode = new Node(e);
//        if (head.next == head) {// 链表为空时
//            head.next = newNode;
//            newNode.next = head;// 新结点指向虚拟头结点
//        }else {// 不为空时，直接在头部添加，新结点指向头结点，虚拟头结点指向新结点
//            newNode.next = head.next;
//            head.next = newNode;
//        }
//        size++;
        add(1,e);// 直接调用下标添加，头结点下标为1
    }

    public void addTail(E e){
//        Node newNode = new Node(e);
//        if (head.next == head) {// 链表为空时
//            head.next = newNode;
//            newNode.next = head;
//        }else {// 不为空时，找到尾部，添加，并将其指向虚拟头结点
//            Node current = head;
//            while (current.next != head) {
//                current = current.next;
//            }
//            current.next = newNode;
//            newNode.next = head;
//        }
//        size++;
        add(size+1,e);// 由于虚拟结点不存数据，所以真实长度要大一
    }

    // 删除给定值的第一个结点
    public void deleteValue(E value){
        Node current = head.next;
        Node previous = head;
        if (current == previous) {
            throw new IllegalArgumentException("空链表，不能删除！");
        }else {
            while (current.next != head) {
                if (current.data.equals(value)){
                    previous.next = current.next;
                    current.data = null;
                    current.next = null;
                    size--;
                    break;// 删除一个跳出，多个不跳出
                }
                previous = previous.next;
                current = current.next;
            }
        }
    }

    // 删除给定索引结点
    public E deleteIndex(int index){
        E temp = null;
        if (index < 1 || index > size) {
            throw new IndexOutOfBoundsException("下标错误！");
        }else {
            Node current = head;
            Node previous = head;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            // 循环结束，拿到要删除的元素索引
            previous.next = current.next;
            temp = current.data;
            current.data = null;
            current.next = null;
            size--;
        }
        return temp;
    }

    public E deleteHead(){
        return deleteIndex(1);
    }

    public E deleteTail(){
        return deleteIndex(size);
    }

    // 是否为空，头结点指向头结点是为空
    public boolean isEmpty(){
        return head.next == head;
    }

    // 打印
    public void display(){
        Node current = head;
        while (current.next != head) {
            current = current.next;
            System.out.print(current.data + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircleLinkedList cList = new CircleLinkedList();

        cList.addTail(12);
        cList.display();
        // 12
        cList.addHead(10);
        cList.display();
        // 10 12
        cList.addHead(13);
        cList.addTail(11);
        cList.display();
        // 13 10 12 11
        cList.deleteIndex(3);
        cList.display();

        cList.add(2,15);
        cList.display();

        cList.deleteValue(15);
        cList.display();


    }

}
