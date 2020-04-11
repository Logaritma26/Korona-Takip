package com.log.koronatakip;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("countries")
    Call<List<Countries>> getCountries();


    @GET("live/country/{slugCountry}/status/confirmed")
    Call<List<SpesificData>> getSpesificData(@Path("slugCountry") String slugCountry);
}
