package org.example.guava.listener;

import com.google.common.eventbus.Subscribe;
import org.example.guava.event.AEvent;
import org.example.guava.event.BEvent;

/**
 * TwoLinstener
 *
 * @author: Diammd
 * @since: 2023/8/5
 */
public class TwoListener {

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
