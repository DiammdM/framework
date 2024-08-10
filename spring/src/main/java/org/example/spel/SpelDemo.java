package org.example.spel;

import lombok.Data;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * SpelDemo
 *
 * @author: Diammd
 * @since: 2024/8/9
 */
public class SpelDemo {

  /**
   * 基础入门
   */
  public void demo1() {
    // 1.定义解析器
    SpelExpressionParser parser = new SpelExpressionParser();
    // 2.使用解析器解析表达式
    Expression expression = parser.parseExpression("'xxx'");
    // 3.获取解析结果Ø
    String value = (String)expression.getValue();
    System.out.println(value);
  }

  /**
   * 字符串方法的字面调用
   */
  public void demo2() {
    // 1.定义解析器
    SpelExpressionParser parser = new SpelExpressionParser();
    // 2.使用解析器解析表达式
    Expression expression = parser.parseExpression("'xxx'.concat('yyy')");
    // 3.获取解析结果Ø
    String value = (String)expression.getValue();
    System.out.println(value);
    expression = parser.parseExpression("'xxx'.bytes");
    byte[] bytes = (byte[]) expression.getValue();
    System.out.println(bytes);
    expression = parser.parseExpression("'xxx'.bytes.length");
    int length = (int) expression.getValue();
    System.out.println(length);

  }

  /**
   * 针对特定对象解析表达式
   */
  public void demo3() {
    User user = new User();
    user.setName("zhangsan");
    // 1.定义解析器
    SpelExpressionParser parser = new SpelExpressionParser();
    // 2.使用解析器解析表达式
    Expression expression = parser.parseExpression("name");
    // 3.获取解析结果Ø
    String value = (String)expression.getValue(user);
    System.out.println(value);

    // 2.1 使用解析器解析表达式，获取对象的属性值并进行运算
    Expression exp2 = parser.parseExpression("name == 'zhangsan'");
    // 3.1 获取解析结果
    Boolean value1 = exp2.getValue(user, Boolean.class);
    System.out.println(value1);
  }

  public static void main(String[] args) {
    SpelDemo spelDemo = new SpelDemo();
    spelDemo.demo3();
  }

  @Data
  public class User{
    private String name;
  }
}
