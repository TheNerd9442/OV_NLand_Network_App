package com.dielinke.ovnland.network;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String START_URL = "https://dielinke-ovnland.de/public_Register/login.php";
    private static final String ALLOWED_HOST = "dielinke-ovnland.de";

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webview = new WebView(this);
        setContentView(webview);

        WebSettings ws = webview.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setDatabaseEnabled(true);
        ws.setAllowFileAccess(false);
        ws.setAllowContentAccess(false);
        ws.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webview, true);
        }

        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                Uri uri = Uri.parse(url);
                String host = uri.getHost();
                if (host != null && host.endsWith(ALLOWED_HOST)) {
                    return false;
                }
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
                return true;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                return shouldOverrideUrlLoading(view, url);
            }
        });

        webview.loadUrl(START_URL);
    }

    @Override
    public void onBackPressed() {
        WebView w = (WebView) findViewById(android.R.id.content).getRootView().findViewById(android.R.id.content);
        if (w != null && w.canGoBack()) {
            w.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
