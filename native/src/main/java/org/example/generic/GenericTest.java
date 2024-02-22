package org.example.generic;
/**
 * 泛型方法:
 * 1.必须在参数上使用泛型
 * 2.泛型声明必须在返回值类型之前 例如：<T> void
 * 3.泛型方法实际是通过入参的类型做的判断，所以不像泛型类，需要在使用时指定泛型的类型
 *
 * @author: Diammd
 * @create: 2024/2/22 14:36
 */
public class GenericTest {
  public <T> void fun1(T t) {
    System.out.println(t.getClass().toString());
  }

  public <T> T func2(T t) {
    return t;
  }

  public <T, M> void func3(T t, M m) {
    System.out.println(t.getClass().toString());
    System.out.println(m.getClass().toString());
  }

  public <T, M> T func4(T t, M m) {
    return t;
  }

  public static void main(String[] args) {
    GenericTest genericTest = new GenericTest();
    genericTest.fun1(1);
    genericTest.fun1("meng");

    Integer integer = genericTest.func2(1);
    System.out.println(integer);
    String s = genericTest.func2("meng");
    System.out.println(s);

    genericTest.func3(1, "meng");

    Integer integer1 = genericTest.func4(1, "meng");
    System.out.println(integer1);
  }
}
