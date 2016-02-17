package com.clockworkant.skylark.setsoverview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.clockworkant.skylark.R;
import com.clockworkant.skylark.SkylarkApplication;
import com.clockworkant.skylark.api.SkylarkClient;
import com.clockworkant.skylark.api.model.SkylarkSet;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SetsActivity extends AppCompatActivity {

    private SkylarkClient skylarkClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        skylarkClient = SkylarkApplication.getInstance(getApplicationContext()).getSkylarkClient();

        RecyclerView recycleView = (RecyclerView) findViewById(R.id.sets_recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        SetsAdapter adapter = new SetsAdapter(getApplicationContext());
        recycleView.setAdapter(adapter);

        fetchSets(adapter);
    }

    private void fetchSets(final SetsAdapter adapter) {

        skylarkClient.fetchSets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<SkylarkSet>>() {
                    @Override
                    public void call(List<SkylarkSet> skylarkSets) {
                        adapter.setSets(skylarkSets);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
