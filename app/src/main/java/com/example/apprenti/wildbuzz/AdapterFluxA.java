package com.example.apprenti.wildbuzz;


import java.util.*;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by apprenti on 18/04/17.
 */


public class AdapterFluxA extends RecyclerView.Adapter<AdapterFluxA.ViewHolder> {
    private Context context;
    private List <ContributionsA> contributions;

    public AdapterFluxA (Context context, java.util.List<ContributionsA> uploads) {
        this.contributions = uploads;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contributions_challengea_item, null);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContributionsA participation = contributions.get(position);



        Glide.with(context).load(participation.getmImgContributionsA()).placeholder(R.mipmap.placeholdercontributions).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return contributions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.contributionImage);

        }
    }

}