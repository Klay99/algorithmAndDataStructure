package sorts;

import java.util.Arrays;

/**
 * @program: algorithmAndDataStructure
 * @description: 希尔排序，对比插入排序，改变插入间隔，插入排序中会比较每个元素直到找到对应位置，
 *               这样同一个数会被重复的比较多次，因此扩大了比较间隔。
 *               希尔排序中，间隔会由大到小最后变成1（即插入排序），
 *               常用的间隔序列：3*h+1，当数组长度为100时：h=40,13,4,1。外循环的迭代条件为：h /= 3。
 * @author: Koty
 * @create: 2019-10-18 18:51
 **/
public class ShellSort<T extends Comparable<T>> {

    public T[] sort(T[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && array[j].compareTo(array[j-h]) < 0; j -= h) {
                    T temp = array[j];
                    array[j] = array[j-h];
                    array[j-h] = temp;
                }
            }
            h /= 3;
        }

        return array;
    }

    public static void main(String[] args) {
        ShellSort<Integer> ss = new ShellSort<>();
        Integer[] a = {4,2,8,9,5,7,6,1,3,10};
        System.out.println(Arrays.toString(ss.sort(a)));
    }

}
