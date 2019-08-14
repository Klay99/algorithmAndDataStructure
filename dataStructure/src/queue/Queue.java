package queue;

import array.MyArray;

/**
 * 循环队列，单向队列（只能在队尾插入，在对头删除）
 */
public class Queue<E> {
    private int front;// 指向对头元素的指针
    private int rear;// 指向队尾元素的指针
    private int size;// 队列的大小
    private int tSize;// 队列中的实际有效长度
    private MyArray<E> myArray;

    // 默认队列大小为10
    public Queue() {
        front = 0;
        rear = -1;
        tSize = 0;
        size = 10;
        myArray = new MyArray<>(size);
    }

    // 自定义队列大小
    public Queue(int size) {
        front = 0;
        rear = -1;
        tSize = 0;
        this.size = size;
        myArray = new MyArray<>(size);
    }

    // 得到头指针
    public int getFront(){
        return front;
    }

    // 得到尾指针
    public int getRear(){
        return rear;
    }

    // 得到实际大小
    public int gettSize(){
        return tSize;
    }

    // 得到队列大小
    public int getSize(){
        return size;
    }

    // 是否队空
    public boolean isEmpty(){
        return (tSize == 0);
    }

    // 是否队满
    public boolean isFull(){
        return (tSize == size);
    }

    /**
     * 入队，从队尾插入，队尾指向队顶时将队尾重新指向队底
     *
     * @param value 待入队的值
     * @return 入队成功返回真
     */
    public boolean enqueue(E value){
        if (isFull()) {
            System.out.println("队满");
        }else {
            if (rear == size - 1) {
                rear = -1;
            }
            rear++;
            myArray.add(rear,value);
            tSize++;
            return true;
        }
        return false;
    }

    /**
     * 出队，队头指向队列顶时重新指向队底
     *
     * @return 返回出队元素
     */
    public E dequeue(){
        if (isEmpty()) {
            System.out.println("对空");
            return null;
        }
        E temp = myArray.getByIndex(front);
        myArray.setByIndex(front,null);
        front++;
        if (front == size) {
            front = 0;
        }
        tSize--;
        return temp;
    }


    /**
     * 实现双向队列，对头和队尾都可以进行入队和出队操作
     *
     */

    /**
     * 在对头进行入队操作，当对头到达队底时将对头指向队顶
     * 由于在对头入队，当队列为空时队尾指向-1，这时要将队尾指向入队的第一个元素（及队尾元素）
     *
     * @param value 待入队的值
     * @return 入队成功返回真
     */
    public boolean enqueueFront(E value){
        if (isFull()) {
            System.out.println("队满，无法入队");
        }else {
            front--;
            if (front == -1) {
                front = size - 1;
            }
            if (rear == -1) {
                rear = front;
            }
            myArray.add(front, value);
            tSize++;
            return true;
        }
        return false;
    }

    // 在队尾出队
    public E dequeueRear(){
        if (isEmpty()) {
            System.out.println("队空，无法出队");
        }else {
            E temp = myArray.getByIndex(rear);
            rear--;
            if (rear == -1) {
                rear = size - 1;
            }
            tSize--;
            return temp;
        }
        return null;
    }

    // 访问元素
    public E get(int index){
        return myArray.getByIndex(index);
    }

    public static void main(String[] args) {
        Queue<Object> queue = new Queue<>();
        System.out.println("入队测试");
        for (int i = 0; i < queue.size; i++) {
            queue.enqueue("q" + i);
            System.out.print("\t"+i+":" + queue.get(i));
        }
        System.out.println("\tfront:" + queue.getFront() + "\trear:" + queue.getRear() +
                            "\ttSize:" + queue.gettSize() + "\tsize:" + queue.getSize());

        System.out.println("\n出队测试");
        for (int i = 0; i < queue.size; i++) {
            System.out.print("\tout("+i+"):" + queue.dequeue());
        }
        System.out.println("\tfront:" + queue.getFront() + "\trear:" + queue.getRear() +
                            "\ttSize:" + queue.gettSize() + "\tsize:" + queue.getSize());

        System.out.println("\n");
        Queue<Object> queue1 = new Queue<>();
        queue1.enqueue(0);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(1);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(2);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(3);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(4);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(5);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(6);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(7);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(8);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(9);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.dequeue();
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.dequeue();
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(9);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());
        queue1.enqueue(9);
        System.out.println("\tfront:" + queue1.getFront() + "\trear:" + queue1.getRear() + "\ttSize:" + queue1.gettSize() + "\tsize:" + queue1.getSize());


        Queue<Object> queue2 = new Queue<>();
        System.out.println("\n\n测试对头入队");
        for (int i = 0; i < queue2.size; i++) {
            queue2.enqueueFront(i);
            System.out.print("\tfront:" + queue2.getFront() + "\t" + queue2.get(queue2.getFront()));
        }

//        for (int i = 0; i < queue2.size; i++) {
//            queue2.enqueueFront("q" + i);
//            System.out.print("\t"+i+":" + queue2.get(i));
//        }

        System.out.println("\tfront:" + queue2.getFront() + "\trear:" + queue2.getRear() +
                "\ttSize:" + queue2.gettSize() + "\tsize:" + queue2.getSize());
        System.out.println(queue2.myArray.toString());

//        System.out.println("\n出队测试");
//        for (int i = 0; i < queue2.size; i++) {
//            System.out.print("\tout("+i+"):" + queue2.dequeue());
//        }
//        System.out.println("\tfront:" + queue2.getFront() + "\trear:" + queue2.getRear() +
//                "\ttSize:" + queue2.gettSize() + "\tsize:" + queue2.getSize());

        System.out.println("\n测试队尾出队");
        for (int i = 0; i < queue2.size; i++) {
            System.out.print("\tout("+ (i+1) +",rear:" + queue2.getRear() + "):" + queue2.dequeueRear());
        }
        System.out.println("\tfront:" + queue2.getFront() + "\trear:" + queue2.getRear() +
                "\ttSize:" + queue2.gettSize() + "\tsize:" + queue2.getSize());

    }

}
