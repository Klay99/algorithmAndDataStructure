package sorts;

/**
 * @program: algorithmAndDataStructure
 * @description: 插入排序
 * @author: Koty
 * @create: 2019-10-17 17:21
 **/
public class InsertionSort<T extends Comparable<T>> {

    public T[] sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = i;
            T temp = array[i];
            while (j > 0 && temp.compareTo(array[j-1]) < 0) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = temp;
        }

        return array;
    }

    public static void main(String[] args) {
        InsertionSort<Integer> is = new InsertionSort<>();
        Integer[] a = {1,2,4,3,8,6,7};
        Integer[] b = is.sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "\t");
        }
    }

}
