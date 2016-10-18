package com.bodhixu.teademo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bodhixu.teademo.R;
import com.bodhixu.teademo.activity.DetailActivity;
import com.bodhixu.teademo.adapter.NewsAdapter;
import com.bodhixu.teademo.constant.MyConstants;
import com.bodhixu.teademo.info.NewsInfo;
import com.bodhixu.teademo.util.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 茶叶列表的Fragment
 */
public class TeaListFragment extends Fragment implements NewsAdapter.IOnItemClickListener{

    @BindView(R.id.tea_rv)
    RecyclerView teaRv;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    //数据的url
    private String url;

    //页面的类型
    private int type;

    //新闻的数据源
    private List<NewsInfo.DataBean> newsDatas;
    //适配器
    private NewsAdapter newsAdapter;

    public static final int STATE_UPDETE = 100;
    public static final int STATE_REFRESH_OVER = 101;
    //刷新画面的Hanndler
    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == STATE_UPDETE) {
                newsAdapter.notifyDataSetChanged();
            }
            if (msg.what == STATE_REFRESH_OVER) {
                refreshLayout.setRefreshing(false);
            }


        }
    };

    //当前页数
    private int currentPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tea_list, container, false);
        ButterKnife.bind(this, view);

        //取类型
        Bundle bundle = getArguments();
        type = bundle.getInt(MyConstants.KEY_FRAGMENT_TEY);
        url = bundle.getString(MyConstants.KEY_FRAGMENT_URL_TEY);

        //从网络取数据
        getData(currentPage);
        //加载视图
        initView();

        //下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //从网络取数据
                currentPage++;
                getData(currentPage);
            }
        });

        return view;
    }

    private void initView() {
        newsDatas = new ArrayList<>();
        newsAdapter = new NewsAdapter(getActivity(), newsDatas, this);
        teaRv.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
        teaRv.setAdapter(newsAdapter);
    }

    private void getData(int page) {

        String newUrl = null;
        if (type == 0) { //头条
            newUrl = String.format(url, page);
        } else {
            newUrl = String.format(url, page, type);
        }

        OkHttpUtils.doGETRequest(newUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = updateHandler.obtainMessage();
                msg.what = STATE_REFRESH_OVER;
                updateHandler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = updateHandler.obtainMessage();
                msg.what = STATE_REFRESH_OVER;
                updateHandler.sendMessage(msg);

                String result = OkHttpUtils.getStringFromResponse(response);
                //json解析
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    String dataArrayTxt = dataArray.toString();

                    TypeToken<List<NewsInfo.DataBean>> typeToken = new
                            TypeToken<List<NewsInfo.DataBean>>() {
                            };
                    List<NewsInfo.DataBean> datas = gson.fromJson(dataArrayTxt, typeToken
                            .getType());
                    newsDatas.addAll(0, datas);
                    Message msg2 = updateHandler.obtainMessage();
                    msg2.what = STATE_UPDETE; //
                    updateHandler.sendMessage(msg2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onclick(int position) {
        //点击item跳转到详情画面
        String id = newsDatas.get(position).getId();
        String title = newsDatas.get(position).getTitle();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(MyConstants.KEY_DETAIL_ID, id);
        intent.putExtra(MyConstants.KEY_DETAIL_TITLE, title);
        startActivity(intent);
    }
}
