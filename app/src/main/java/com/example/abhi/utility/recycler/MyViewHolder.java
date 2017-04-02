package com.example.abhi.utility.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhi.utility.R;

/**
 * Created by abhi on 26/2/17.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img;
    TextView nameText;
    ItemClickListener itemClickListener;


    public MyViewHolder(View itemView) {
        super(itemView);

        nameText = (TextView) itemView.findViewById(R.id.option_name);
        img = (ImageView) itemView.findViewById(R.id.option_image);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }


    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }


}
