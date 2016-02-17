package com.clockworkant.skylark.setsoverview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clockworkant.skylark.R;
import com.clockworkant.skylark.api.model.SkylarkSet;

import java.util.ArrayList;
import java.util.List;

class SetsAdapter extends RecyclerView.Adapter<SetsViewholder> {

    private final LayoutInflater layoutInflater;
    private List<SkylarkSet> sets;

    public SetsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        sets = new ArrayList<>();
    }

    @Override
    public SetsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SetsViewholder(v);
    }

    @Override
    public void onBindViewHolder(SetsViewholder holder, int position) {
        holder.bind(sets.get(position));
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }

    public void setSets(List<SkylarkSet> sets) {
        this.sets = sets;
    }
}
