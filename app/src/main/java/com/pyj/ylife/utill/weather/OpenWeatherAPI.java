package com.pyj.ylife.utill.weather;

import com.pyj.ylife.dto.OpenWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherAPI {
    //Interface 정의
    //API를 사용하는데 위치정보, api사용자 고유번호를 넣어주는곳
    @GET("weather")
    Call<OpenWeather> getWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String appid);
}
