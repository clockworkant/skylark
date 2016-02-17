package com.clockworkant.skylark;

import android.app.Application;
import android.content.Context;

import com.clockworkant.skylark.api.SkylarkClient;

/**
 * Created by alec on 17/02/2016.
 */
public class SkylarkApplication extends Application {

    private SkylarkClient skylarkClient;

    public static SkylarkApplication getInstance(Context context) {
        return (SkylarkApplication) context.getApplicationContext();
    }

    public SkylarkClient getSkylarkClient() {
        if(skylarkClient == null) {
            skylarkClient = new SkylarkClient();
        }
        return skylarkClient;
    }
}
