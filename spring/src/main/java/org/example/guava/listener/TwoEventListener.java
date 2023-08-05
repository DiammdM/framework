package org.example.guava.listener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.example.guava.event.AEvent;
import org.example.guava.event.BEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * TwoEventListener
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
@Component
public class TwoEventListener {

    @Resource
    EventBus eventBus;

    @PostConstruct
    public void init(){
        eventBus.register(this);
    }

    @Subscribe
    public void actionA(AEvent event){
        System.out.println("----TwoListener");
        System.out.println(event);
        System.out.println("----TwoListener");
    }

    @Subscribe
    public void actionB(BEvent event){
        System.out.println("----TwoListener");
        System.out.println(event);
        System.out.println("----TwoListener");
    }

}
