package org.example.spel.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * LogSpelAop
 * 1.在context设置变量，通过#变量，调用
 *
 * @author: Diammd
 * @since: 2024/8/9
 */
@Aspect
@Component
public class LogSpelAopAspect {

  private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

  @Pointcut("@annotation(org.example.spel.aop.LogSpel)")
  public void pointCut() {}

  @Around("pointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    // printLog(joinPoint);
    printLogBean(joinPoint);
    Object proceed = joinPoint.proceed();
    return proceed;
  }

  /**
   * 非bean方法
   * @param joinPoint
   * @throws IllegalAccessException
   */
  public void printLog(ProceedingJoinPoint joinPoint) throws IllegalAccessException {
    // 1.构建上下文
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setRootObject(joinPoint.getTarget());
    fillContext(context, joinPoint);

    // 2.定义解析器
    SpelExpressionParser parser = new SpelExpressionParser();

    // 3.获取表达式并执行
    MethodSignature signature = (MethodSignature)joinPoint.getSignature();
    LogSpel logSpel = signature.getMethod().getAnnotation(LogSpel.class);
    Expression expression = parser.parseExpression(logSpel.spelStr());

    String value = expression.getValue(context, String.class);

    System.out.println(value);
  }

  /**
   * 调用bean的方法
   * @param joinPoint
   * @throws IllegalAccessException
   */
  public void printLogBean(ProceedingJoinPoint joinPoint) throws IllegalAccessException {

    // 1.创建 BeanFactory
    // 这里使用注解配置的方式创建 BeanFactory
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(AnotherBean.class).getBeanFactory();
    BeanFactoryResolver beanFactoryResolver = new BeanFactoryResolver(beanFactory);

    // 2.构建上下文
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setBeanResolver(beanFactoryResolver);
    fillContext(context, joinPoint);

    // 3.定义解析器
    SpelExpressionParser parser = new SpelExpressionParser();

    // 4.获取表达式并执行
    MethodSignature signature = (MethodSignature)joinPoint.getSignature();
    LogSpel logSpel = signature.getMethod().getAnnotation(LogSpel.class);
    Expression expression = parser.parseExpression(logSpel.spelStr());

    String value = expression.getValue(context, String.class);

    System.out.println(value);
  }

  private void fillContext(EvaluationContext context, ProceedingJoinPoint joinPoint) throws IllegalAccessException {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String[] parameterNames = nameDiscoverer.getParameterNames(signature.getMethod());
    for (int i = 0; i < joinPoint.getArgs().length; i++) {
      context.setVariable(parameterNames[i], joinPoint.getArgs()[i]);
    }
  }

}
