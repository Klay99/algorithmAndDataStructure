package sorts;

/**
 * @program: algorithmAndDataStructure
 * @description: 冒泡排序
 * @author: Koty
 * @create: 2019-10-17 16:19
 **/
public class BubbleSort<T extends Comparable<T>> {

    public T[] sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j+1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        return array;
    }

    public static void main(String[] args) {
        BubbleSort<Integer> bs = new BubbleSort<>();
        Integer[] a = {4,3,2,1,0,6,5};
        Integer[] b = bs.sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "\t");
        }
    }

}
