package com.example.abhi.utility.Main_fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhi.utility.R;
import com.example.abhi.utility.TabWebSocial;
import com.example.abhi.utility.recycler.MyRecyclerAdapter;
import com.example.abhi.utility.recycler.Option;

import java.util.ArrayList;


public class WebFrag extends Fragment {

    public int array[] = {0,0,0};
    public char arrayC[]={};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_social,null);
        //Recycler

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.socialrec);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rv.setAdapter(new MyRecyclerAdapter(this.getActivity(),getMainOptions()));

        CardView cardView= (CardView)v.findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                final String[] items = {"facebook","instagram","twitter"};
                final ArrayList itemsSelected = new ArrayList();


                AlertDialog.Builder builder = new AlertDialog.Builder(WebFrag.this.getActivity());
                builder.setTitle("Select the websites you need");
                builder.setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedItemId,
                                                boolean isSelected) {
                                if (isSelected) {
                                   // Toast.makeText(WebFrag.this.getContext(),Integer.valueOf(selectedItemId),LENGTH_LONG).show();
                                    array[selectedItemId] = 1;
                                    itemsSelected.add(selectedItemId);
                                } else if (itemsSelected.contains(selectedItemId)) {
                                    array[selectedItemId] = 0;
                                    itemsSelected.remove(Integer.valueOf(selectedItemId));
                                }
                            }
                        })
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Your logic when OK button is clicked
                                for (int i=0;i<2;i++) {
                                 //   arrayC[i] = Character.forDigit(array[i], 10);
                                  //  Toast.makeText(WebFrag.this.getContext(), itemsSelected.indexOf(i), LENGTH_LONG).show();
                                }
                                Intent intent = new Intent(WebFrag.this.getActivity(), TabWebSocial.class);
                                intent.putExtra("numbers", array);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                dialog = builder.create();
                dialog.show();
            }
        });

        return v;

    }

    private ArrayList<Option> getMainOptions() {
        ArrayList<Option> options =new ArrayList<>();

        Option option = new Option(R.drawable.google,"google");
        options.add(option);

        option = new Option(R.drawable.fb,"facebook");
        options.add(option);

        option = new Option(R.drawable.tw,"twitter");
        options.add(option);

        option = new Option(R.drawable.ins,"instagram");
        options.add(option);

        option = new Option(R.drawable.linkdin,"linkedin");
        options.add(option);

        option = new Option(R.drawable.quora,"quora");
        options.add(option);

        option = new Option(R.drawable.happy,"b");
        options.add(option);

        option = new Option(R.drawable.happy,"b");
        options.add(option);

        option = new Option(R.drawable.happy,"b");
        options.add(option);

        option = new Option(R.drawable.happy,"b");
        options.add(option);



        return options;



    }

    @Override
    public String toString() {
        return "Web";
    }
}