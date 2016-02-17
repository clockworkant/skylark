package com.clockworkant.skylark.setdetail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.clockworkant.skylark.EpisodeActivity;
import com.clockworkant.skylark.R;
import com.clockworkant.skylark.api.model.Episode;

class EpisodeViewholder extends RecyclerView.ViewHolder {
    private final TextView title;
    private Episode episode;

    public EpisodeViewholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = EpisodeActivity.getStartIntent(v.getContext(), episode);
                startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(startIntent);
            }
        });
        title = (TextView) itemView.findViewById(R.id.rv_item_text);
    }

    public void bind(Episode episode) {
        String title = episode.getTitle();
        this.title.setText(TextUtils.isEmpty(title) ? "<Title Unknown>" : title);
        this.episode = episode;
    }
}
