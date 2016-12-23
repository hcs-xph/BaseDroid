package com.mph.library.event;

/**
 * Created by：hcs on 2016/12/22 17:14
 * e_mail：aaron1539@163.com
 */
public interface IBus {
    void register(Object object);
    void unregister(Object object);
    void post(IEvent event);
    void postSticky(IEvent event);

    interface IEvent{
        int getTag();
    }
}
