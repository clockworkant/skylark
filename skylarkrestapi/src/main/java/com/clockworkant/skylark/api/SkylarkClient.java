package com.clockworkant.skylark.api;

import com.clockworkant.skylark.api.model.*;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;


public class SkylarkClient {

    private final SkylarkService service;
    public static final String BASE_URL = "http://feature-code-test.skylark-cms.qa.aws.ostmodern.co.uk:8000";

    public SkylarkClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .client(client)
                .build();

        service = retrofit.create(SkylarkService.class);
    }

    public Observable<List<SkylarkSet>> fetchSets() {
        return service.fetchSets().map(new Func1<SkylarkSetsResponse, List<SkylarkSet>>() {
            @Override
            public List<SkylarkSet> call(SkylarkSetsResponse skylarkSetsResponse) {
                return skylarkSetsResponse.objects;
            }
        });
    }

    public Observable<Episode> fetchEpisode(Item item){
        return service.fetchEpisode(item.getContent_url());
    }

}
