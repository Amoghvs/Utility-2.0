package com.example.abhi.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Introact extends AppCompatActivity {

    private ViewPageAdapter viewPageAdapter;
    private ViewPager viewPager;
    private TextView[] dots;
    Button next,skip;
    private LinearLayout dotslayout;
    private int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences("lol",0);
        int appCurrentBuildVersion = BuildConfig.VERSION_CODE;
        int appLastBuildVersion = sharedPreferences.getInt("app first",0);
        if (appLastBuildVersion == appCurrentBuildVersion)
        {
            //not a first run
            callMainAct();

        }
        else
        {
            sharedPreferences.edit().putInt("app first",appCurrentBuildVersion).apply();
            if (appLastBuildVersion ==0){
                //first run
                startActivityIntroact();
            }
            else {
                //updated
                startActivityIntroact();
            }
        }

        startActivityIntroact();


    }

    public void startActivityIntroact()
    {
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_introact);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotslayout = (LinearLayout)findViewById(R.id.layoutDots);
        skip=(Button)findViewById(R.id.btnskip);
        next=(Button)findViewById(R.id.btnnext);
        layouts = new int[] {R.layout.introlayout1, R.layout.introlayout2, R.layout.introlayout3};
        addBottomDots(0);
        changeStatusBarColor();
        viewPageAdapter = new ViewPageAdapter();
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMainAct();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current<layouts.length) {
                    viewPager.setCurrentItem(current);
                }
                else {
                    callMainAct();
                }

            }
        });

    }

    public void callMainAct()
    {
        Intent i = new Intent(Introact.this, MainActivity.class);
        startActivity(i);
        finish();

    }

    private void addBottomDots(int position) {
        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotslayout.removeAllViews();
        for(int i = 0; i < dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotslayout.addView(dots[i]);
        }
        if(dots.length > 0)
        {
            dots[position].setTextColor(colorActive[position]);
        }
    }

    private int getItem(int i)
    {
        return viewPager.getCurrentItem() +1;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position ==layouts.length - 1){
                next.setText("Proceed");
                skip.setVisibility(View.GONE);
            }
            else
            {
                next.setText("Next");
                skip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }
    public class ViewPageAdapter extends PagerAdapter{

        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View)object;
            container.removeView(v);
        }
    }
}
