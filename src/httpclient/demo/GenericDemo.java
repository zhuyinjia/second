package httpclient.demo;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

    public static void test1() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("aaaa");
//        arrayList.add(100); //使用了泛型<String>，编译就会报错

        for(int i = 0; i< arrayList.size();i++){
            String item = (String)arrayList.get(i);
            System.out.print(item);
        }
    }

    /*通过下面的例子可以证明，在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，
    只在编译阶段有效。在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦出，
    并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
    也就是说，泛型信息不会进入到运行时阶段*/
    public static void test2() {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.print("类型相同");
        }
    }

    public static void main(String arg[]) throws Exception {
        test1();
        test2();
    }
}

