package com.example.abhi.utility.web_fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.abhi.utility.R;
import com.example.abhi.utility.TabWebSocial;

/**
 * Created by abhi on 28/2/17.
 */

public class FB extends Fragment {
     WebView mainWebView;
    Context c;
    String webpage;
    TabWebSocial tabWebSocial;

    public FB() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.webview, container,false);
        // webpage = getArguments().getString("webpage");
         //Toast.makeText(c,webpage,Toast.LENGTH_LONG).show();

        String website = "http://www.facebook.com";

        //switch (webpage){
          //  case "tw" : website = "http://www.facebook.com";break;
           // case "ins" : website = "http://www.instagram.com";break;
           // default: break;
        //}


        mainWebView = (WebView) vi.findViewById(R.id.webView1);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainWebView.loadUrl(website);


        mainWebView.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });

        return vi;
    }




    class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }




}

