package com.example.abhi.utility.Main_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.utility.R;
import com.example.abhi.utility.recycler.MyRecyclerAdapter;
import com.example.abhi.utility.recycler.Option;

import java.util.ArrayList;

/**
 * Created by abhi on 26/2/17.
 */

public class MainFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main,null);

        //Recycler

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.mainrec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setAdapter(new MyRecyclerAdapter(this.getActivity(),getMainOptions()));
        return v;
    }

    private ArrayList<Option> getMainOptions() {
        ArrayList<Option> options =new ArrayList<>();

        Option option = new Option(R.drawable.calculator,"calculator");
        options.add(option);

        option = new Option(R.drawable.flashlight,"flashlight");
        options.add(option);

        option = new Option(R.drawable.compass,"compass");
        options.add(option);

        option = new Option(R.drawable.timer,"timer");
        options.add(option);

        option = new Option(R.drawable.calendar,"calendar");
        options.add(option);

        option = new Option(R.drawable.bmi,"bmi");
        options.add(option);

        option = new Option(R.drawable.notes,"notes");
        options.add(option);


        option = new Option(R.drawable.rul,"ruler");
        options.add(option);

        option = new Option(R.drawable.happy,"b");
        options.add(option);

        option = new Option(R.drawable.happy,"b");
        options.add(option);

        return options;



    }

    @Override
    public String toString() {
        return "Main";
    }
}
