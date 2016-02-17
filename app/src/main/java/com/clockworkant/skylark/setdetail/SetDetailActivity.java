package com.clockworkant.skylark.setdetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.clockworkant.skylark.R;
import com.clockworkant.skylark.api.model.Item;
import com.clockworkant.skylark.api.model.SkylarkSet;

import java.util.ArrayList;
import java.util.List;

public class SetDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ITEMS = "extra.items";
    private static final String EXTRA_TITLE = "extra.title";
    private static final String EXTRA_BODY = "extra.body";
    private static final String EXTRA_TEMP_IMAGE = "extra.temp.image";

    private List<Item> items;

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

        populateItems();
        setSetTitle(getIntent().getStringExtra(EXTRA_TITLE));
        setSetBody(getIntent().getStringExtra(EXTRA_BODY));
    }

    private void populateItems() {
        ArrayList<ParcelableItem> parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(EXTRA_ITEMS);
        items = new ArrayList<Item>(parcelableArrayListExtra);

        for (Item item : items) {
            System.out.println(item.getContent_url());
        }
    }

    public void setSetTitle(String setTitle) {
        ((TextView)findViewById(R.id.setdetail_title)).setText(setTitle);
    }

    public void setSetBody(String setBody) {
        ((TextView)findViewById(R.id.setdetail_body)).setText(setBody);
    }

}
