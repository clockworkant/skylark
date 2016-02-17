package com.clockworkant.skylark.setdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.clockworkant.skylark.R;
import com.clockworkant.skylark.SkylarkApplication;
import com.clockworkant.skylark.api.SkylarkClient;
import com.clockworkant.skylark.api.model.Episode;
import com.clockworkant.skylark.api.model.Item;
import com.clockworkant.skylark.api.model.SkylarkSet;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SetDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ITEMS = "extra.items";
    private static final String EXTRA_TITLE = "extra.title";
    private static final String EXTRA_BODY = "extra.body";
    private static final String EXTRA_TEMP_IMAGE = "extra.temp.image";
    private SkylarkClient skylarkClient;

    public static Intent getStartIntent(Context context, SkylarkSet set) {
        Intent intent = new Intent(context, SetDetailActivity.class);
        ArrayList<ParcelableItem> pItems = new ArrayList<>();
        for (Item item: set.getItems()) {
            pItems.add(new ParcelableItem(item));
        }

        intent.putParcelableArrayListExtra(EXTRA_ITEMS, pItems);
        intent.putExtra(EXTRA_TITLE, set.getTitle());
        intent.putExtra(EXTRA_BODY, set.getBody());
        intent.putExtra(EXTRA_TEMP_IMAGE, set.getTempImage());

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_list);

        skylarkClient = SkylarkApplication.getInstance(getApplicationContext()).getSkylarkClient();
        populateItems(skylarkClient);
        setSetTitle(getIntent().getStringExtra(EXTRA_TITLE));
        setSetBody(getIntent().getStringExtra(EXTRA_BODY));
    }

    private void populateItems(SkylarkClient client) {

        RecyclerView recycleView = (RecyclerView) findViewById(R.id.setsdetail_recycler_view);

        recycleView.setLayoutManager(new LinearLayoutManager(this));

        final EpisodeAdapter adapter = new EpisodeAdapter(getApplicationContext());
        recycleView.setAdapter(adapter);

        ArrayList<ParcelableItem> parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(EXTRA_ITEMS);

        for (Item item : parcelableArrayListExtra) {
            client.fetchEpisode(item)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Episode>() {
                        @Override
                        public void call(Episode episode) {
                            adapter.add(episode);
                            adapter.notifyItemInserted(adapter.getItemCount());
                        }
                    });
        }
    }

    public void setSetTitle(String setTitle) {
        ((TextView)findViewById(R.id.setdetail_title)).setText(setTitle);
    }

    public void setSetBody(String setBody) {
        ((TextView)findViewById(R.id.setdetail_body)).setText(setBody);
    }

}
