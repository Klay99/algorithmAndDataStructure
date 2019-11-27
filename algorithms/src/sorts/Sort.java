package sorts;

import java.util.Arrays;

/**
 * @program: algorithmAndDataStructure
 * @description:
 * @author: Koty
 * @create: 2019-10-18 16:01
 **/
public class Sort<T extends Comparable<T>> {

    public T[] bubbleSort(T[] array) {
        print(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) >= 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                print(array);
            }
        }
        return array;
    }

    public T[] selectionSort(T[] array) {
        print(array);
        for (int i = 0; i < array.length; i++) {
            int min = i;
            int j = i + 1;
            for (; j < array.length; j++) {
                if (array[j].compareTo(array[min]) <= 0) {
                    min = j;
                }
            }
            T temp = array[min];
            array[min] = array[i];
            array[i] = temp;
            print(array);
        }

        return array;
    }

    public T[] insertionSort(T[] array) {
        print(array);
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int j = i;
            while (j > 0 && temp.compareTo(array[j - 1]) <= 0) {
                array[j] = array[j - 1];
                print(array);
                j--;
            }
            array[j] = temp;
            print(array);
        }
        return array;
    }

    public T[] quickSort(T[] array) {
        print(array);
        qSort(array, 0, array.length - 1);
        return array;
    }

    public void qSort(T[] array, int left, int right) {
        if (left < right) {
            int partition = partition(array, left, right);
            qSort(array, left, partition);
            qSort(array, partition + 1, right);
        }
    }

    public int partition(T[] array, int left, int right) {
        T temp = array[left];
        System.out.println("partition:\t" + temp);
        while (left < right) {
            while (left < right && array[right].compareTo(temp) > 0) {
                right--;
            }
            array[left] = array[right];
            print(array);
            while (left < right && array[left].compareTo(temp) < 0) {
                left++;
            }
            array[right] = array[left];
            print(array);
        }
        array[left] = temp;
        print(array);
        return left;
    }

    public T[] mergeSort(T[] array) {
        print(array);
        T[] t = (T[]) new Comparable[array.length];
        mSort(array, t, 0, array.length - 1);
        return array;
    }

    public void mSort(T[] array, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mSort(array, temp, left, mid);
            mSort(array, temp, mid + 1, right);
            merge(array, temp, left, mid, right);
        }
    }

    public void merge(T[] array, T[] temp, int left, int mid, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
            print(array);
        }
        while (i <= mid) {
            array[k++] = temp[i++];
            print(array);
        }
        while (j <= right) {
            array[k++] = temp[j++];
            print(array);
        }
    }

    public T[] shellSort(T[] array) {
        print(array);
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            System.out.println("h=" + h + ":");
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && array[j].compareTo(array[j - h]) <= 0; j -= h) {
                    T temp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = temp;
                    print(array);
                }
            }

            h /= 3;
        }

        return array;
    }

    public void print(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        Sort<Integer> s = new Sort<>();
        Integer[] a = {6, 5, 3, 1, 8, 7, 2, 4};

//        s.bubbleSort(a);
//        s.selectionSort(a);
//        s.insertionSort(a);
//        s.quickSort(a);
//        s.mergeSort(a);
        s.shellSort(a);
    }

}
