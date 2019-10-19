package stack;

import array.MyArray;

/**
 * 栈
 */
public class Stack<E> {
    private int top;// 指向栈顶元素的指针
    private int size;// 总容量
    private MyArray<E> myArray;// 用于存储数据的数组

    // 默认栈的大小为10
    public Stack(){
        top = -1;
        size = 10;
        myArray = new MyArray<E>();
    }

    // 自定义栈的大小
    public Stack(int size) {
        top = -1;
        this.size = size;
        myArray = new MyArray<>(size);
    }

    // 是否为空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈，栈满自动扩容
    public void push(E value){
        myArray.add(++top,value);// 将指针上移，添加元素
    }

    // 获取栈顶元素
    public E pick(){
        return myArray.getByIndex(top);
    }

    // 出栈
    public E pop(){
        return myArray.removeByIndex(top--);
    }


    public static void main(String[] args) {
        Stack<Object> stack = new Stack<Object>(10);

        System.out.println("入栈测试");
        for (int i = 0; i < stack.size; i++) {
            stack.push("h" + i);
            System.out.print("\t" + stack.pick());
        }
        System.out.println("(top)\n出栈测试");
        for (int i = stack.size-1; i > -1; i--) {
            System.out.print("\t" + stack.pick() + "(top:"+i+")");
            stack.pop();

        }
        System.out.println("\n\n判断分隔符是否匹配");

        //判断分隔符是否匹配
        String s2 = "a[01(r{}fe)]";// 匹配
        String s3 = "12{1[10(a])]}";// 不匹配
        String s1 = s3;
        Stack<Object> stack2 = new Stack<>(s1.length());
        char c;
        boolean isMatch = true;
        // 当遇到左分隔符时入栈，遇到右分隔符时出栈，并判断是否匹配
        for (int i = 0; i < s1.length(); i++) {
            c = s1.charAt(i);
            if (c == '{' || c == '[' || c == '('){
                stack2.push(c);
            }else if (c == '}' || c == ']' || c == ')'){
                char c2 = (char)stack2.pop();
                if ( !(c2 == '{' && c == '}' || c2 == '[' && c == ']' || c2 == '(' && c == ')') ) {
                    isMatch = false;
                    System.out.println("c2:'" + c2 + "'\tc:'" + c + "'");
                    break;// 只要有一次不匹配结果就为不匹配，跳出循环（右分隔符可能比左分隔符多）
                }
            }
        }
        if (isMatch) {
            System.out.println("匹配");
        }else {
            System.out.println("不匹配");
        }

    }

}
