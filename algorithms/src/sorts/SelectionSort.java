package sorts;

/**
 * @program: algorithmAndDataStructure
 * @description: 选择排序
 * @author: Koty
 * @create: 2019-10-17 17:08
 **/
public class SelectionSort<T extends Comparable<T>> {

    public T[] sort(T[] array){
        int min;
        for (int i = 0; i < array.length; i++) {
            min = i;
            for (int j = i; j < array.length; j++){
                if (array[j].compareTo(array[min]) < 0){
                    min = j;
                }
            }
            T temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }

        return array;
    }

    public static void main(String[] args) {
        SelectionSort<Integer> ss = new SelectionSort();
        Integer[] a = {5,4,3,2,1,0,8};
        Integer[] b = ss.sort(a);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + "\t");
        }
    }

}
