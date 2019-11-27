package heap;

/**
 * @program: algorithmAndDataStructure
 * @description: 最大堆，所有节点的值都不大于其父节点，且都不小于其子节点
 * @author: Koty
 * @create: 2019-11-26 10:41
 **/
public class MaxHeap<T extends Comparable<T>> {

    private T[] maxHeap; // 数组实现

    private int capacity; // 容量

    private int size; // 大小

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        size = 0;
        maxHeap = (T[]) new Comparable[capacity];
    }

    // 直接赋值一个数组
    public MaxHeap(T[] array, int capacity) {
        if (array.length > capacity) {
            return;
        }
        this.capacity = capacity;
        size = array.length;
        maxHeap = (T[]) new Comparable[capacity];
        System.arraycopy(array, 0, maxHeap, 0, array.length);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean insert(T value) {
        if (isFull()) {
            return false;
        }
        maxHeap[size] = value; // 将新值放到最后
        toggleUp(size++); // 向上调整堆
        return true;
    }

    // 向上调整
    private void toggleUp(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index: " + index);
        int parentIndex = (index - 1) / 2; // 父节点索引
        T temp = maxHeap[index];
        // 找到需要调整到的位置，直到小于父节点的值
        while (index > 0 && maxHeap[index].compareTo(maxHeap[parentIndex]) > 0) {
            maxHeap[index] = maxHeap[parentIndex]; // 父节点的值小于当前节点，交换父子节点位置
            index = (index - 1) / 2;
        }
        maxHeap[index] = temp; // 循环结束，调整到对应的位置，所有节点的值都小于父节点的值
        display();
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The heap is empty");
        }
        T temp = maxHeap[0]; // 移除堆顶元素
        maxHeap[0] = maxHeap[--size]; // 最末尾元素调整到堆顶
        toggleDown(0); // 向下调整
        return temp;
    }

    // 向下调整
    private void toggleDown(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index: " + index);
        T temp = maxHeap[index];
        int largerChildIndex; // 两个子节点中较大的一个的索引
        while (index < size / 2) { // 所有叶子节点都不需要向下调整
            int leftChildIndex = index * 2 + 1; // 左子节点索引
            int rightChildIndex = index * 2 + 2; // 右子结点索引
            // 找出较大的子节点
            if (maxHeap[leftChildIndex].compareTo(maxHeap[rightChildIndex]) < 0) {
                largerChildIndex = rightChildIndex;
            } else {
                largerChildIndex = leftChildIndex;
            }
            if (temp.compareTo(maxHeap[largerChildIndex]) > 0) {
                break; // 如果当前节点的值比较大的子节点的值还要大
            }
            maxHeap[index] = maxHeap[largerChildIndex];
            index = largerChildIndex;
        }
        maxHeap[index] = temp;
        display();
    }

    public void display() {
        int counts = 1, next = 2;
        for (int i = 0; i < size; i++) {
            if (counts == 0) {
                System.out.println();
                counts = next;
                next *= 2;
            }
            System.out.print(maxHeap[i] + "\t");
            counts--;
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Integer[] arr = {8,6,4,1,5,3};
        MaxHeap<Integer> maxHeap = new MaxHeap<>(arr, 7);
        maxHeap.display();
        maxHeap.insert(7);
        maxHeap.toggleDown(maxHeap.size / 2 - 1);
        maxHeap.remove();
    }

}
