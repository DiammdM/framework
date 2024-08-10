package org.example.spel.aop;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * SpelController
 *
 * @author: Diammd
 * @since: 2024/8/9
 */
@RestController
@RequestMapping("spel")
public class SpelController {

  @Resource
  AnotherBean anotherBean;

  /**
   * 调用当前类的方法
   * @param spelPara
   * @return
   */
  @PostMapping("test")
  @LogSpel(spelStr="#this.log(#spelPara.name,#spelPara.age,#spelPara.sex)")
  public String test(@RequestBody SpelPara spelPara) {
    return "success";
  }

  /**
   * 调用bean方法
   * @param spelPara
   * @return
   */
  @PostMapping("test1")
  @LogSpel(spelStr="@anotherBean.log(#spelPara.name,#spelPara.age,#spelPara.sex)")
  public String test1(@RequestBody SpelPara spelPara) {
    return "success";
  }

  public String log(Object... object) {
    String result = "";
    for (Object o : object) {
      result = result + "_" + o;
    }
    return result;
  }

}
