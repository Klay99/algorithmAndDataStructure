package array;

import java.util.Arrays;

/**
 * 动态数组
 */
public class MyArray<E> {
    private E[] array;
    private int length;// 最大长度
    private int tlength;// 实际有效长度

    // 默认长度为50的数组
    public MyArray() {
        tlength = 0;
        length = 5;
        array = (E[])new Object[length];
    }

    // 自定义数组长度
    public MyArray(int length) {
        tlength = 0;
        this.length = length;
        array = (E[])new Object[length];
    }

    // 得到数组实际有效长度
    public int getTlength(){
        return tlength;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // 重新定义数组最大长度，将原数组拷贝到一个新数组，将新数组赋给原数组
    public void relength(int newLength){
        E[] newArray = (E[])new Object[newLength];
        for (int i = 0; i < tlength; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        length = newLength;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    // 添加到指定下标，包括给定下标及后面的所有元素后移一位
    public void add(int index, E value) {
        if (tlength == length) {// 当数组满了时，将数组扩充两倍
            relength(2 * length);
        }
        if(index < 0 || index >= length){
            throw new IllegalArgumentException("index illegal");
        }

        if (array[index] != null) {
            for (int i = tlength; i > index; i--) {
                array[i] = array[i-1];
            }
        }
        array[index] = value;
        tlength++;
    }

    // 头部添加
    public void addFirst(E value){
        add(0,value);
    }

    // 尾部添加
    public void addLast(E value){
        add(tlength,value);
    }

    // 根据下标获取元素
    public E getByIndex(int index){
        if(index < 0 || index >= length){
            System.out.println("访问下标越界！");
        }
        return array[index];
    }

    // 根据值获取元素下标
    public int getByValue(E value){
        int i;
        for (i = 0; i < tlength; i++) {
            if (array[i].equals(value)){
                break;
            }
        }
        if (i == tlength){
            return -1;
        }
        return i;
    }

    // 根据下标删除元素
    public E removeByIndex(int index){
        E element;
        if (index < 0 || index >= length){
            throw new IllegalArgumentException("下标越界！");
        }else {
            element = array[index];
            for (int i = index; i < tlength-1; i++) {
                array[i] = array[i+1];
            }
            setByIndex(tlength-1,null);
            tlength--;
        }
        return element;
    }
    // 根据值删除元素
    public boolean removeByValue(E value){
        for (int i = 0; i < tlength; i++) {
            if (value.equals(array[i])) {
                removeByIndex(i);
                break;
            }
        }
        return true;
    }

    // 根据下标修改
    public boolean setByIndex(int index,E value){
        if (index < 0 || index >= tlength) {
            throw new IllegalArgumentException("下标越界！");
        }else {
            array[index] = value;
            return true;
        }
    }

    // 根据值修改
    public boolean setByValue(E value,E newValue){
        for (int i = 0; i < tlength; i++) {
            if (array[i].equals(value)) {
                array[i] = newValue;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MyArray<Object> myArray = new MyArray<Object>();

        myArray.add(0,0);
        myArray.add(1,1);
        myArray.add(2,2);
        myArray.add(3,3);
        myArray.add(4,4);
        myArray.add(5,5);
        myArray.add(6,6);
        myArray.add(7,7);
        myArray.add(8,8);
        myArray.add(9,9);

        myArray.removeByIndex(3);
        myArray.removeByIndex(1);

        myArray.removeByValue(0);
        myArray.removeByValue(7);

        myArray.setByIndex(0,1);
        myArray.setByValue(9,90);

        System.out.println("intArray["+1+"]:"+ myArray.getByIndex(1));
        System.out.println("indexOf("+8+"):"+ myArray.getByValue(8));

        myArray.addFirst("f");
        myArray.addLast("d");
        System.out.println(myArray.toString());
    }


}
