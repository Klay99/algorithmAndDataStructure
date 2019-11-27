package sorts;

import java.util.Arrays;

/**
 * @program: algorithmAndDataStructure
 * @description: 堆排序
 * @author: Koty
 * @create: 2019-11-27 16:11
 **/
public class HeapSort<T extends Comparable<T>> {

    // 1.把数组初始化成堆，只需从最后一个非叶子节点处依次向上执行toggleDown操作，直到变成堆
    // 这里初始化为最大堆，end为未排序数组的末尾元素索引，未排序前end为array.length-1
    public void initHeap(T[] array, int end) {
        if (array == null || array.length == 0) {
            return;
        }
        System.out.println("build heap:");
        boolean isHeap = true; // 是否为堆
        for (int i = (end + 1) / 2 - 1; i >= 0; i--) { // 从最后一个非叶子节点处开始
            int largerChildIndex; // 两个子节点中较大的一个的索引
            int leftChildIndex = i * 2 + 1;
            int rightChildIndex = i * 2 + 2;
            // 由于从 (end+1)/2-1 处开始遍历，所以左子节点不可能超过 end，只用判断right
            if (rightChildIndex <= end && array[rightChildIndex].compareTo(array[leftChildIndex]) > 0) {
                largerChildIndex = rightChildIndex;
            } else {
                largerChildIndex = leftChildIndex;
            }
            if (array[i].compareTo(array[largerChildIndex]) < 0) { // 如果当前节点值比较大的子节点的值要小
                swap(array, i, largerChildIndex); // 交换当前节点与较大子节点
                isHeap = false; // 发生了交换，所以还不是堆
            }
            display(array);
        }
        if (!isHeap) { // 递归执行，直到数组变成堆
            initHeap(array, end);
        }
    }

    // 2.排序，由于是最大堆，所以堆顶最大应放在数组末尾（这里为升序）
    public T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int end = array.length - 1; // end为未排序数组的末尾元素索引
        initHeap(array, end);
        System.out.println("\nsort:");
        while (end >= 0) {
            swap(array, 0, end); // 堆顶与未排序的最后一个元素交换位置
            initHeap(array, --end); // 初始化堆
        }
        return array;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void display(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        HeapSort<Integer> heapSort = new HeapSort<>();
        Integer[] array = {10,11,12,5,52,1,100};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(heapSort.sort(array)));
    }

}
