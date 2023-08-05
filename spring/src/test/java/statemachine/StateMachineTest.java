package statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * StateMachineTest
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
public class StateMachineTest {

  static String MACHINE_ID = "TestStateMachine";

  static enum States {
    STATE1,
    STATE2,
    STATE3,
    STATE4
  }

  static enum Events {
    EVENT1,
    EVENT2,
    EVENT3,
    EVENT4,
    INTERNAL_EVENT
  }

  static class Context {
    String operator = "frank";
    String entityId = "123465";

    @Override
    public String toString() {
      return "Context{" + "operator='" + operator + '\'' + ", entityId='" + entityId + '\'' + '}';
    }
  }

  @Test
  public void testExternalNormal() {
    StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
    builder
        .externalTransition()
        .from(States.STATE1)
        .to(States.STATE2)
        .on(Events.EVENT1)
        .when(checkCondition())
        .perform(doAction());

    StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID);
    States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());
    Assert.assertEquals(target, States.STATE2);
  }

  private Condition<Context> checkCondition() {
    return new Condition<Context>() {
      @Override
      public boolean isSatisfied(Context context) {
        System.out.println("Check condition : " + context);
        return true;
      }
    };
  }

  private Action<States, Events, Context> doAction() {
    return new Action<States, Events, Context>() {
      @Override
      public void execute(States from, States to, Events event, Context ctx) {
        System.out.println(
            ctx.operator
                + " is operating "
                + ctx.entityId
                + " from:"
                + from
                + " to:"
                + to
                + " on:"
                + event);
      }
    };
  }
}
