package sorts;

/**
 * @program: algorithmAndDataStructure
 * @description: 归并排序,归并排序的比较次数小于快速排序的比较次数，移动次数一般多于快速排序的移动次数。
 * @author: Koty
 * @create: 2019-10-18 15:02
 **/
public class MergeSort<T extends Comparable<T>> {

    public T[] sort(T[] array) {
        T[] t = (T[]) new Comparable[array.length];
        doSort(array, t, 0, array.length - 1);
        return array;
    }

    public void doSort(T[] array, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            doSort(array, temp, left, mid);
            doSort(array, temp, mid + 1, right);
            merge(array, temp, left, mid, right);
        }
    }

    public void merge(T[] array, T[] temp, int left, int mid, int right) {
        // 每进行一次合并前，将array的left到right的所有值赋给temp
        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left; // 左半部分索引
        int j = mid + 1; // 右半部分索引
        int k = left; // 合并索引
        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                array[k++] = temp[i++];
            }else {
                array[k++] = temp[j++];
            }
        }
        while (i <= mid) { // 当右边已添加完，将左边的全部添加进去
            array[k++] = temp[i++];
        }
        while (j <= right) { // 当左边已添加完，将右边的全部添加进去
            array[k++] = temp[j++];
        }
    }

    public static void main(String[] args) {
        MergeSort<Integer> ms = new MergeSort<>();
        Integer[] a = {6,5,3,1,8,7,2,4};
        Integer[] b = ms.sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "\t");
        }
    }

}
