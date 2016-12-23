package com.mph.library.imgload;

/**
 * Created by：hcs on 2016/12/23 09:44
 * e_mail：aaron1539@163.com
 */
public class ILFactory {

    private static ILoader loader;

    public static ILoader getLoader(){
        if(loader == null){
            synchronized (ILFactory.class){
                if(loader == null){
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }

}
