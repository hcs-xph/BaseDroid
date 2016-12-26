package com.mph.basedroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.mph.basedroid.adapter.CityAdapter;
import com.mph.basedroid.entity.City;
import com.mph.library.base.BaseActivity;
import com.mph.library.log.XLog;
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

public class CityActivity extends BaseActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.emptyLayout)
    EmptyContentLayout emptyLayout;

    private MyOkHttp okHttp;
    private CityAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        okHttp = MyApp.getInstance().getOkHttp();
        emptyLayout.emptyView(LayoutInflater.from(this).inflate(R.layout.view_empty,null));
        emptyLayout.loadingView(LayoutInflater.from(this).inflate(R.layout.view_loading,null));
        emptyLayout.errorView(LayoutInflater.from(this).inflate(R.layout.view_error,null));

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
                        XLog.json(response.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        XLog.d("CityActivity", error_msg);
                        emptyLayout.showError();
                    }
                });

    }
}
