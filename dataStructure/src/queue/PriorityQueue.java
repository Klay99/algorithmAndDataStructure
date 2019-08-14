package queue;

import array.MyArray;

/**
 * 优先队列
 * 在队列中，数据项按照关键字进行排序
 * 关键字最小（或者最大）的数据项往往在队列的最前面，且优先级最高
 * 而数据项在插入的时候都会插入到合适的位置以确保队列的有序
 */
public class PriorityQueue<E extends Comparable<E> > {

    /**
     * The size of the queue
     */
    private int size;
    /**
     * The array of the queue
     */
    private MyArray<E> myArray;
    /**
     * How many items are in the queue
     */
    private int nItems;

    /**
     * 默认队列大小为10
     */
    public PriorityQueue() {
        size = 10;
        nItems = 0;
        myArray = new MyArray<>(size);
    }

    /**
     * 自定义队列大小
     *
     * @param size
     */
    public PriorityQueue(int size) {
        this.size = size;
        nItems = 0;
        myArray = new MyArray<>(size);
    }

    /**
     * 是否队空
     */
    public boolean isEmpty(){
        return (nItems == 0);
    }

    /**
     * 是否队满
     */
    public boolean isFull(){
        return (nItems == size);
    }

    public int getnItems(){
        return nItems;
    }

    /**
     * 入队，按关键字从大到小排序，最大的在对头，最小的在队尾，所有比入队元素大的全部上移，对头在最上面
     */
    public boolean enqueue(E value){
        if (isFull()) {
            System.out.println("队满，无法入队");
        }else {
            int i = nItems;// 由于是上移，从最上面的元素开始
            if (i == 0) {// 对空时直接入队
                myArray.add(i, value);
            }else {// 不空时，按关键字排序入队，比入队元素大的全部上移array[i] > value
                while (i > 0 && compare(myArray.getByIndex(i-1),value) > 0 ) {//
                    //myArray.setByIndex(i,myArray.getByIndex(i-1));//① 直接将元素上移
                    //② MyArray中的添加会让添加的索引位置空出来（元素后移），此处可以不用上面的方式实现上移
                    i--;
                }
                //① 循环结束，空出了入队元素的入队位置
                //myArray.setByIndex(i,value);//① 改变空出的位置的值
                //② 循环结束，得到入队元素的索引
                myArray.add(i, value);//② 根据MyArray的添加实现元素上移
            }
            nItems++;
            return true;
        }
        return false;
    }

    /**
     * 出队，对头优先级最高，对头元素出队
     * @return 返回对头元素
     */
    public E dequeue(){
        return myArray.getByIndex(--nItems);
    }

    /**
     * 拿到优先级最高的元素
     * @return 返回对头元素
     */
    public E peek(){
        return myArray.getByIndex(nItems - 1);
    }

    // 比较a和b，a.compareTo(b)，a“大于”b时返回1，“等于”返回0，“小于”返回-1
    public int compare(E a, E b){
        return a.compareTo(b);
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();

        System.out.println("入队测试");
//        for (int i = pQueue.size-1; i >= 0; i--) {
//            pQueue.enqueue(i);
//        }
        pQueue.enqueue(10);
        pQueue.enqueue(2);
        pQueue.enqueue(5);
        pQueue.enqueue(3);
        System.out.println(pQueue.myArray.toString());
        System.out.println("\n出队测试");
        for (int i = 0; i < 4; i++) {
            System.out.print(pQueue.dequeue() + "\t");
        }

    }

}
