package other;

public class TryCatchDemo {
    public static void main(String[] args) {
        A a = new A();
        a.setName("hello");
        changeA(a);
        System.out.println(a.getName());
        //
        System.out.println(test(""));
        // integer 内部有缓存
        Integer tmp = Integer.valueOf(89);
        Integer tmp2 = 89;
        System.out.println(tmp == tmp2);
        System.out.println(tmp == 89);
        Integer tmp3 = Integer.valueOf(289);
        Integer tmp4 = 289;
        System.out.println(tmp3 == tmp4);
        System.out.println(tmp3 == 289);
    }

    public static String test(String str) {
        try {
            int i = 1 / 0;
            str = "hello";
            return str;
        } catch (Exception e) {
            str = "ex";
            return str;
        } finally {
            str = "s";
        }
    }


    public static void changeA(A a) {
        a.setName("world");
        a = new A();
        a.setName("xxx");
    }
}

class A {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
