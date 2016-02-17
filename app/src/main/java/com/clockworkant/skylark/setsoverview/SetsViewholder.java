package com.clockworkant.skylark.setsoverview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.clockworkant.skylark.R;
import com.clockworkant.skylark.api.model.SkylarkSet;
import com.clockworkant.skylark.setdetail.SetDetailActivity;

class SetsViewholder extends RecyclerView.ViewHolder {

    private final TextView title;
    private SkylarkSet set;

    public SetsViewholder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = SetDetailActivity.getStartIntent(v.getContext(), set);
                startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(startIntent);
            }
        });
        title = (TextView) itemView.findViewById(R.id.rv_item_text);

    }

    public void bind(SkylarkSet skylarkSet) {
        title.setText(skylarkSet.getTitle());
        set = skylarkSet;
    }
}
