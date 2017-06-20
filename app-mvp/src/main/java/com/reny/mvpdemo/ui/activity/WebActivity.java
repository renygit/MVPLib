package com.reny.mvpdemo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.reny.mvpdemo.BuildConfig;
import com.reny.mvpdemo.R;
import com.reny.mvpdemo.core.MyBaseActivity;
import com.reny.mvpdemo.databinding.ActivityWebBinding;
import com.reny.mvpvmlib.base.RBasePresenter;

public class WebActivity extends MyBaseActivity<ActivityWebBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected RBasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initWebViewSettings();

        if(null != getIntent()){
            String url = getIntent().getStringExtra("url");
            if(TextUtils.isEmpty(url))url = "about:blank";
            binding.webView.loadUrl(url);
        }

        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        binding.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                binding.toolbar.setTitle(title);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                binding.pb.setProgress(newProgress);
                if (newProgress != 100) {
                    binding.pb.setVisibility(View.VISIBLE);
                } else {
                    binding.pb.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }

    @Override
    public void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    private void initWebViewSettings() {
        binding.webView.setVerticalScrollBarEnabled(false);
        WebSettings webSetting = binding.webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //webSetting.setSupportZoom(true);
        // webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }
        //Android 5.0中使用webView 加载网页不显示图片问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

}
