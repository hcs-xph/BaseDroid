package com.mph.library.event;

/**
 *
 * Created by：hcs on 2016/12/22 17:13
 * e_mail：aaron1539@163.com
 */
public class BusFactory {

    private static IBus bus;

    public static IBus getBus(){
        if(bus == null){
            synchronized (BusFactory.class){
                if(bus == null){
                    bus = new EventBusImpl();
                }
            }
        }
        return bus;
    }
}
