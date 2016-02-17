package com.clockworkant.skylark.api;

import com.clockworkant.skylark.api.model.Episode;
import com.clockworkant.skylark.api.model.SkylarkSetsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface SkylarkService {
    @GET("api/sets/")
    Observable<SkylarkSetsResponse> fetchSets();

    @GET
    Observable<Episode> fetchEpisode(@Url String url);
}
