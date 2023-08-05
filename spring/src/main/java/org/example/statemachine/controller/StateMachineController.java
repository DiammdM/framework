package org.example.statemachine.controller;

import com.alibaba.cola.statemachine.StateMachine;
import org.example.statemachine.context.Context;
import org.example.statemachine.events.Events;
import org.example.statemachine.state.States;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * StateMachineController
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
@RestController
@RequestMapping("statemachine")
public class StateMachineController {

  @Resource
  StateMachine<States, Events, Context> stateMachine;

  @GetMapping("event1")
  public Object event1(@RequestBody Context context) {
    States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, context);
    System.out.println(target);
    return target;
  }

  @GetMapping("event2")
  public Object event2(@RequestBody Context context) {
    States target = stateMachine.fireEvent(States.STATE1, Events.EVENT2, context);
    System.out.println(target);
    return target;
  }


}
