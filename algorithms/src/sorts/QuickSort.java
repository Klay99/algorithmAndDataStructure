package sorts;

/**
 * @program: algorithmAndDataStructure
 * @description: 快速排序
 * @author: Koty
 * @create: 2019-10-18 10:39
 **/
public class QuickSort<T extends Comparable<T>> {

    public T[] sort(T[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 排序过程
     *
     * @param array 需要排序的数组
     * @param low 当前数组的低位（第一个元素）索引
     * @param high 当前数组的高位（最后一个元素）索引
     */
    public void doSort(T[] array, int low, int high) {
        if (low < high) { // 当低位小于高位时满足条件
            int partition = partition(array, low, high); // 拿到基数后，对数组进行左右子序列的划分
            doSort(array, low, partition - 1); // 对基数的左子序列进行排序
            doSort(array, partition + 1, high); // 对基数的右子序列进行排序
        }
    }

    /**
     * 切分（partition）数组，获取基数并排序
     * 将基数保存起来并挖一个坑，先从高往低找比基数小的数，填上一个坑，且该位成为新的坑，
     * 再从低往高找比基数大的数，填上一个坑，且该位成为新的坑。
     * 依次类推，循环结束，最后一个坑填上基数，到此数组已排序
     *
     * @param array 需要获取基数的数组
     * @param low 数组的低位
     * @param high 数组的高位
     * @return 当前数组的基数索引，排序结束后基数的左边都比基数小右边都比基数大
     */
    public int partition(T[] array, int low, int high) {
        // 将当前数组的第一个元素作为基数，挖掉一个坑（低位）
        T temp = array[low];
        while (low < high) {
            // 从高位开始，从高往低找比基数小的数
            while (low < high && array[high].compareTo(temp) >= 0) {
                high--;
            }
            array[low] = array[high]; // 找到比基数小的数后，填上低位的坑，高位变成坑
            // 从低位开始，从低往高找比基数大的数
            while (low < high && array[low].compareTo(temp) <= 0) {
                low++;
            }
            array[high] = array[low]; // 找到比基数大的数后，填上高位的坑，低位变成坑
        }
        // 循环结束，low的左边都比temp小，low的右边都比temp大
        array[low] = temp; // 将基数填上low的坑

        return low;
    }

    public static void main(String[] args) {
        QuickSort<Integer> qs = new QuickSort<>();
        Integer[] a = {3,4,1,6,0,2};
        Integer[] b = qs.sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "\t");
        }
    }

}
