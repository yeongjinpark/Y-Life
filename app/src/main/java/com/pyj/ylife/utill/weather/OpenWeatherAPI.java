package com.pyj.ylife.utill.weather;

import com.pyj.ylife.dto.OpenWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherAPI {

    @GET("weather")
    Call<OpenWeather> getWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appid);
}
