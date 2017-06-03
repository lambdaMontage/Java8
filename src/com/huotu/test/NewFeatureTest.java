package com.huotu.test;

/**
 * Created by mensonges on 2017/6/3.
 */
public class NewFeatureTest {

    @org.junit.Test
    public void test1(){
        NewFeatureTest newFeatureTest = new NewFeatureTest();

        // 带有类型声明的表达式
        MathOperation addition = (int a, int b) -> a + b;

        // 没有类型声明的表达式
        MathOperation subtraction = (a, b) -> a - b;

        // 带有大括号、带有返回语句的表达式
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号和return语句的表达式
        MathOperation division = (int a, int b) -> a / b;

        // 输出结果
        System.out.println("10 + 5 = " + newFeatureTest.operate(100, 2, addition));
        System.out.println("10 - 5 = " + newFeatureTest.operate(100, 2, subtraction));
        System.out.println("10 x 5 = " + newFeatureTest.operate(100, 2, multiplication));
        System.out.println("10 / 5 = " + newFeatureTest.operate(100, 2, division));

        // 没有括号的表达式
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 有括号的表达式
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        /**
         *
         */
        // 调用sayMessage方法输出结果
        greetService1.sayMessage("Shiyanlou");
        greetService2.sayMessage("Classmate");

    }


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }


    /**
     *  可访问 static 修饰的成员变量，如果是 final static 修饰，不可再次赋值，只有 static 修饰可再次赋值；
         可访问表达式外层的 final 局部变量（不用声明为 final，隐性具有 final 语义），不可再次赋值。
     */
    final static String salutation = "Hello "; //正确，不可再次赋值
    //static String salutation = "Hello "; //正确，可再次赋值
    //String salutation = "Hello "; //报错
    //final String salutation = "Hello "; //报错

    public static void main(String args[]){
        //final salutation = "Hello "; //正确，不可再次赋值
        //String salutation = "Hello "; //正确，隐性为 final , 不可再次赋值

        // salution = "welcome to "
        GreetingService greetService1 = message ->
                System.out.println(salutation + message);
        greetService1.sayMessage("Shiyanlou");
    }


}
