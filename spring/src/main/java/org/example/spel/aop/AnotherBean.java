package org.example.spel.aop;

import org.springframework.stereotype.Component;

/**
 * AnotherResolver
 *
 * @author: Diammd
 * @since: 2024/8/9
 */
@Component
public class AnotherBean {

  public String log(Object... object) {
    String result = "";
    for (Object o : object) {
      result = result + "-" + o;
    }
    return result;
  }
}
