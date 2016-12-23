package com.mph.library.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by：hcs on 2016/12/23 15:29
 * e_mail：aaron1539@163.com
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity :
                activities) {
            activity.finish();
        }
    }
}
