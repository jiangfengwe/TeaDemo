package com.bodhixu.teademo.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bodhixu.teademo.R;
import com.bodhixu.teademo.constant.MyConstants;
import com.bodhixu.teademo.constant.URLConstants;
import com.bodhixu.teademo.util.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyStore;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {

    private String url;
    private String title;

    private String htmlStr;

    private Toolbar detailToolbar;

    private WebView detailWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initData();
        initToolBar();
        initWebView();
        initWebViewData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放webview资源
        detailWebView.stopLoading();
        ((ViewGroup)detailWebView.getParent()).removeView(detailWebView);
        detailWebView.removeAllViews();
        detailWebView.clearCache(true);
        detailWebView.clearHistory();
        detailWebView.destroy();
    }

    private void initWebViewData() {
        OkHttpUtils.doGETRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //解析数据
                String result = OkHttpUtils.getStringFromResponse(response);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject dataJsonObj = jsonObject.getJSONObject("data");
                    htmlStr = dataJsonObj.getString("wap_content");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            detailWebView.loadDataWithBaseURL(null, htmlStr, "text/html", "UTF-8", null);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(MyConstants.KEY_DETAIL_ID);
        url = String.format(URLConstants.DETAIL_URL, id);
        title = intent.getStringExtra(MyConstants.KEY_DETAIL_TITLE);
    }

    private void initWebView() {
        detailWebView = (WebView) findViewById(R.id.detail_webview);
        detailWebView.getSettings().setJavaScriptEnabled(true);
        detailWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initToolBar() {
        detailToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        detailToolbar.setTitle(title);
        detailToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        setSupportActionBar(detailToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.share:
                Toast.makeText(this, "点击了分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                Toast.makeText(this, "点击了保存", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
