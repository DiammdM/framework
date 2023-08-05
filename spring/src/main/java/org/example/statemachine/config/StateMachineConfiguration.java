package org.example.statemachine.config;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.example.statemachine.context.Context;
import org.example.statemachine.events.Events;
import org.example.statemachine.state.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * StateMachineConfiguration
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
@Configuration
public class StateMachineConfiguration {

    String MACHINE_ID = "TestStateMachine";

    @Bean
    public StateMachine<States, Events, Context> stateMachine() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());

        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE3)
                .on(Events.EVENT2)
                .when(checkCondition())
                .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID);
        return stateMachine;
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
