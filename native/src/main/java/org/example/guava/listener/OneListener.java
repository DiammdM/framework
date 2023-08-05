package org.example.guava.listener;

import com.google.common.eventbus.Subscribe;
import org.example.guava.event.AEvent;
import org.example.guava.event.BEvent;

/**
 * AEventListener
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
public class OneListener {

    @Subscribe
    public void actionA(AEvent event){
        System.out.println("----OneListener");
        System.out.println(event);
        System.out.println("----OneListener");
    }

    @Subscribe
    public void actionB(BEvent event){
        System.out.println("----OneListener");
        System.out.println(event);
        System.out.println("----OneListener");
    }

}
