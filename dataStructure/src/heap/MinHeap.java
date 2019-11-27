package heap;

/**
 * @program: algorithmAndDataStructure
 * @description: 最小堆，所有节点的值都 >= 其父节点，且都 <= 其子节点
 * @author: Koty
 * @create: 2019-11-27 14:17
 **/
public class MinHeap<T extends Comparable> {

    private T[] minHeap; // 数组实现

    private int capacity; // 容量

    private int size; // 大小

    public MinHeap(int capacity) {
        this.capacity = capacity;
        size = 0;
        minHeap = (T[]) new Comparable[capacity];
    }

    // 直接赋值一个数组
    public MinHeap(T[] array, int capacity) {
        if (array.length > capacity) {
            return;
        }
        this.capacity = capacity;
        size = array.length;
        minHeap = (T[]) new Comparable[capacity];
        System.arraycopy(array, 0, minHeap, 0, array.length);
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
        minHeap[size] = value; // 将新值放到最后
        toggleUp(size++); // 向上调整堆
        return true;
    }

    // 向上调整
    private void toggleUp(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index: " + index);
        int parentIndex = (index - 1) / 2; // 父节点索引
        T temp = minHeap[index];
        // 找到需要调整到的位置，直到大于父节点的值
        while (index > 0 && minHeap[index].compareTo(minHeap[parentIndex]) < 0) {
            minHeap[index] = minHeap[parentIndex]; // 父节点的值大于当前节点，交换父子节点位置
            index = (index - 1) / 2;
        }
        minHeap[index] = temp; // 循环结束，调整到对应的位置，所有节点的值都大于父节点的值
        display();
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The heap is empty");
        }
        T temp = minHeap[0]; // 移除堆顶元素
        minHeap[0] = minHeap[--size]; // 最末尾元素调整到堆顶
        toggleDown(0); // 向下调整
        return temp;
    }

    // 向下调整
    private void toggleDown(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Index: " + index);
        T temp = minHeap[index];
        int smallerChildIndex; // 两个子节点中较小的一个的索引
        while (index < size / 2) { // 所有叶子节点都不需要向下调整
            int leftChildIndex = index * 2 + 1; // 左子节点索引
            int rightChildIndex = index * 2 + 2; // 右子结点索引
            // 找出较小的子节点
            if (minHeap[leftChildIndex].compareTo(minHeap[rightChildIndex]) > 0) {
                smallerChildIndex = rightChildIndex;
            } else {
                smallerChildIndex = leftChildIndex;
            }
            if (temp.compareTo(minHeap[smallerChildIndex]) < 0) {
                break; // 如果当前节点的值比较小的子节点的值还要小
            }
            minHeap[index] = minHeap[smallerChildIndex];
            index = smallerChildIndex;
        }
        minHeap[index] = temp;
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
            System.out.print(minHeap[i] + "\t");
            counts--;
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Integer[] arr = {1,3,6,4,8,7};
        MinHeap<Integer> minHeap = new MinHeap<>(arr, 7);
        minHeap.display();
        minHeap.insert(5);
        minHeap.toggleDown(minHeap.size / 2 - 1);
        minHeap.remove();
    }

}
