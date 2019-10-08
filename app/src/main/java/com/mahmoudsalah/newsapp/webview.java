package com.mahmoudsalah.newsapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class webview extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv = findViewById(R.id.wv);
        wv.setWebViewClient(new MyWebViewClient());
        wv.loadUrl(getIntent().getStringExtra("url"));
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals(getIntent().getStringExtra("url"))) {
                // This is my website, so do not override; let my WebView load the page
            }
                return false;
        }
    }
}