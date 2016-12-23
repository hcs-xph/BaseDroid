package com.mph.library.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by：hcs on 2016/12/22 17:26
 * e_mail：aaron1539@163.com
 */
public class EventBusImpl implements IBus {
    @Override
    public void register(Object object) {
        if(!EventBus.getDefault().isRegistered(object)){
            EventBus.getDefault().register(object);
        }
    }

    @Override
    public void unregister(Object object) {
        if(EventBus.getDefault().isRegistered(object)){
            EventBus.getDefault().unregister(object);
        }
    }

    @Override
    public void post(IEvent event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(IEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
