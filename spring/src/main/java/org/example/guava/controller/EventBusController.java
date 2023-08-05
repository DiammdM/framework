package org.example.guava.controller;

import com.google.common.eventbus.EventBus;
import org.example.guava.event.AEvent;
import org.example.guava.event.BEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * EeventBusController
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
@RestController
@RequestMapping("eventbus")
public class EventBusController {
    @Resource
    EventBus eventBus;

    @GetMapping("sendAMessage")
    public void sendAMessage(){
        AEvent aEvent = new AEvent();
        aEvent.setEventId("1");
        aEvent.setMessage("我在跑。。。。");
        eventBus.post(aEvent);
    }

    @GetMapping("sendBMessage")
    public void sendBMessage(){
        BEvent bEvent = new BEvent();
        bEvent.setEventId("1");
        bEvent.setMessage("我在飞。。。。");
        eventBus.post(bEvent);
    }
}
