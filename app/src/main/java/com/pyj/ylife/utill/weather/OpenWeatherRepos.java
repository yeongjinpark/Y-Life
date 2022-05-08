package com.pyj.ylife.utill.weather;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;
import com.pyj.ylife.dto.OpenWeather;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherRepos {
    private final String TAG ="OpenWeatherRepos";
    private final static String URL = "https://api.openweathermap.org/data/2.5/";

    private static OpenWeatherRepos instance;
    private Retrofit retrofit;
    private OpenWeatherAPI openAPI;
    private OpenWeather openWeather;
    private LatLng latLng;
    public static OpenWeatherRepos getInstance(){
        if(instance == null){
            instance = new OpenWeatherRepos();
        }
        return instance;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public MutableLiveData<OpenWeather> getWeather(){
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        openAPI = retrofit.create(OpenWeatherAPI.class);

        openWeather = new OpenWeather();
        MutableLiveData<OpenWeather> data = new MutableLiveData<>();
        callWeatherAPI(data);
        return data;
    }

    private void callWeatherAPI(MutableLiveData<OpenWeather> data){
        Log.i("LatLong", String.valueOf(latLng.latitude));
        Call<OpenWeather> call = openAPI.getWeather(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude),"0b93eae510fc7849431559f5bace3e8e");
        call.enqueue(new Callback<OpenWeather>(){
            @Override
            public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                data.postValue(response.body());
                Log.i(TAG,"Success");
            }

            @Override
            public void onFailure(Call<OpenWeather> call, Throwable t) {
                Log.i(TAG,"Fail"+t.getMessage());
            }
        });
    }
}
