package com.endava.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

   private WebView webView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_web);

      Intent intent = getIntent();
      if(intent != null) {
         String url = intent.getStringExtra("url");
         webView = findViewById(R.id.webView);
         webView.loadUrl(url);
         webView.setWebViewClient(new WebViewClient());
         webView.getSettings().setJavaScriptEnabled(true);
      }
   }

   @Override
   public void onBackPressed() {
      if (webView.canGoBack()) webView.goBack();
      else super.onBackPressed();
   }
}