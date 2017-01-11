package com.mph.basedroid.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.mph.basedroid.MainActivity;
import com.mph.basedroid.MyApp;
import com.mph.basedroid.R;
import com.mph.basedroid.adapter.CityAdapter;
import com.mph.basedroid.entity.City;
import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.response.JsonResponseHandler;
import com.mph.library.util.JsonUtil;
import com.mph.library.view.EmptyContentLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class CityActivity extends CityBaseActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.emptyLayout)
    EmptyContentLayout emptyLayout;

    private MyOkHttp okHttp;
    private CityAdapter adapter;


    @Override
    public void initData(Bundle savedInstanceState) {
        okHttp = MyApp.getInstance().getOkHttp();
        initEmptyView(emptyLayout);
        adapter = new CityAdapter(this);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        emptyLayout.showLoading();
        post();
    }

    @Override
    public void setListener() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        okHttp.cancel(this);
    }

    private void post() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "1");
        okHttp.post().url(MainActivity.APP_URL + "agency/newGetAgency")
                .params(map)
                .tag(this)
                .enqueue(new JsonResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        emptyLayout.showContent();
                        try {
                            JSONArray data = response.getJSONArray("data");
                            List<City> cities = JsonUtil.toObj(new TypeToken<List<City>>() {
                            }, data.toString());
                            adapter.setData(cities);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        xLog.json(response.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        xLog.d("CityActivity", error_msg);
                        emptyLayout.showError();
                    }
                });

    }

    @Override
    public boolean changeStatusBarColor() {
        return true;
    }

    @Override
    public void errorRefresh() {
        post();
    }
}
