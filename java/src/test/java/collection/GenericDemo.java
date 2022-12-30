package collection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

    public static void main(String[] args) throws Exception {
        /*
         * 泛型中的类型擦除：Java编译器会在编译器这个层次上实现的，在生成的字节码中时不会包含泛型中的类型信息，编译之后会把类型变量擦除
         * 保留原始类型Object
         */
        List<Integer> list = new ArrayList<>();
        Method add = list.getClass().getMethod("add", Object.class);
        add.invoke(list, "abc");
        System.out.println(list.get(0));
    }
}
