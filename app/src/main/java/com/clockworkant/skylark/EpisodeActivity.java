package com.clockworkant.skylark;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.clockworkant.skylark.api.model.Episode;

public class EpisodeActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "extra.title";
    private static final String EXTRA_SUBTITLE = "extra.subtitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        ((TextView) findViewById(R.id.episode_title)).setText(getIntent().getStringExtra(EXTRA_TITLE));
        ((TextView) findViewById(R.id.episode_subtitle)).setText(getIntent().getStringExtra(EXTRA_SUBTITLE));
    }

    public static Intent getStartIntent(Context context, Episode episode) {
        Intent intent = new Intent(context, EpisodeActivity.class);
        intent.putExtra(EXTRA_TITLE, episode.getTitle());
        intent.putExtra(EXTRA_SUBTITLE, episode.getSubtitle());
        return intent;
    }
}
