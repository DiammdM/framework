package org.example.guava.exec;

import com.google.common.eventbus.EventBus;
import org.example.guava.event.AEvent;
import org.example.guava.event.BEvent;
import org.example.guava.listener.OneListener;
import org.example.guava.listener.TwoListener;

/**
 * EventBusTest
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
public class EventBusTest {
    public EventBus eventBus = new EventBus();

    public static void main(String[] args) {
        EventBusTest eventBusTest = new EventBusTest();
        eventBusTest.register();
        eventBusTest.sendAMessage();
        eventBusTest.sendBMessage();
    }

    public void register(){
        eventBus.register(new OneListener());
        eventBus.register(new TwoListener());
    }

    public void sendAMessage(){
        AEvent aEvent = new AEvent();
        aEvent.setEventId("1");
        aEvent.setMessage("我在跑。。。。");
        eventBus.post(aEvent);
    }

    public void sendBMessage(){
        BEvent bEvent = new BEvent();
        bEvent.setEventId("1");
        bEvent.setMessage("我在飞。。。。");
        eventBus.post(bEvent);
    }
}
