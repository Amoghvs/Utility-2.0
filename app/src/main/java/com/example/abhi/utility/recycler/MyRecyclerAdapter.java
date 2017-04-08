package com.example.abhi.utility.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.utility.BMI;
import com.example.abhi.utility.BaseActivity;
import com.example.abhi.utility.Calendar;
import com.example.abhi.utility.Compass;
import com.example.abhi.utility.Flashlight;
import com.example.abhi.utility.Notes;
import com.example.abhi.utility.R;
import com.example.abhi.utility.TabWebSocial;
import com.example.abhi.utility.Timer;
import com.example.abhi.utility.WebActivity;
import com.example.abhi.utility.calactivities.CalculatorMainAct;

import java.util.ArrayList;



public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>  {

    Context c;
    ArrayList<Option> options;
    String opn;


    public MyRecyclerAdapter(Context c, ArrayList<Option> options) {
        this.c = c;
        this.options = options;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewlayoutmain, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameText.setText(options.get(position).getOptionname());
        holder.img.setImageResource(options.get(position).getOptionimg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                opn = options.get(pos).getOptionname();
                Intent i = new Intent();
                switch (opn) {
                    case "calculator":
                        i = new Intent(c, CalculatorMainAct.class);
                        break;

                    case "b":
                        i = new Intent(c, TabWebSocial.class);
                        break;

                    case "flashlight":
                        i = new Intent(c, Flashlight.class);
                        break;

                    case "compass":
                        i = new Intent(c, Compass.class);
                        break;

                    case "timer":
                        i = new Intent(c, Timer.class);
                        break;

                    case "calendar":
                        i = new Intent(c, Calendar.class);
                        break;

                    case "bmi":
                        i = new Intent(c, BMI.class);
                        break;

                    case "notes":
                        i = new Intent(c, Notes.class);
                        break;

                    case "Ruler":
                        i = new Intent(c, BaseActivity.class);
                        break;

                    case "facebook":
                        i = new Intent(c, TabWebSocial.class);
                        i.putExtra("website", "fb");
                        break;
                    case "google":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "go");
                        break;
                    case "twitter":
                        i = new Intent(c, TabWebSocial.class);
                        i.putExtra("website", "tw");
                        break;
                    case "instagram":
                        i = new Intent(c, TabWebSocial.class);
                        i.putExtra("website", "in");
                        break;
                    case "linkedin":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "ln");
                        break;
                    case "quora":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "qu");
                        break;
                    case "news":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "ne");
                        break;
                    case "doctors":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "do");
                        break;
                    case "uber":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "ub");
                        break;
                    case "hotels":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "ho");
                        break;
                    case "maps":
                        i = new Intent(c, WebActivity.class);
                        i.putExtra("website", "map");
                        break;

                }

                c.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return options.size();
    }


}

