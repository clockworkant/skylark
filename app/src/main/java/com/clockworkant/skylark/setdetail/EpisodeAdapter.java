package com.clockworkant.skylark.setdetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clockworkant.skylark.R;
import com.clockworkant.skylark.api.model.Episode;

import java.util.ArrayList;
import java.util.List;

class EpisodeAdapter extends RecyclerView.Adapter<EpisodeViewholder> {
    private final LayoutInflater layoutInflater;
    private List<Episode> episodes;

    public EpisodeAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        episodes = new ArrayList<>();
    }

    @Override
    public EpisodeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new EpisodeViewholder(v);
    }

    @Override
    public void onBindViewHolder(EpisodeViewholder holder, int position) {
        holder.bind(episodes.get(position));
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public void add(Episode episode) {
        episodes.add(episode);
    }
}
