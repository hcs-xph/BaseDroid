package com.mph.basedroid.model;

import com.google.gson.reflect.TypeToken;
import com.mph.basedroid.MainActivity;
import com.mph.basedroid.MyApp;
import com.mph.basedroid.entity.City;
import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.response.JsonResponseHandler;
import com.mph.library.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by：hcs on 2017/1/11 16:47
 * e_mail：aaron1539@163.com
 */
public class GetCityInfo implements ICity {

    private MyOkHttp okHttp = MyApp.getInstance().getOkHttp();

    @Override
    public void getCity(final OnGetCityListener listener) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "1");
        okHttp.post().url(MainActivity.APP_URL + "agency/newGetAgency")
                .params(map)
                .tag(this)
                .enqueue(new JsonResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {

                        xLog.d("debug","-------onSuccess---------");

                        if (response != null) {
                            xLog.d("debug","-------onSuccess--if-------");
                            try {
                                JSONArray data = response.getJSONArray("data");
                                List<City> cities = JsonUtil.toObj(new TypeToken<List<City>>() {
                                }, data.toString());
                                listener.onGetCitySuccess(cities);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            xLog.d("debug","-------onSuccess-----else----");
                            listener.onGetCityFailed("");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        xLog.d("CityActivity", error_msg);
                        listener.onGetCityFailed("");
                        xLog.d("debug","-------onFailure---------");
                    }
                });
    }

    @Override
    public void cancel() {
        okHttp.cancel(this);
    }
}
