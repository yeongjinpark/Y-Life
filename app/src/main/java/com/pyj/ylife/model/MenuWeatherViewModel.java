package com.pyj.ylife.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.pyj.ylife.dto.OpenWeather;
import com.pyj.ylife.utill.weather.OpenWeatherRepos;

public class MenuWeatherViewModel extends ViewModel {
    private MutableLiveData<OpenWeather> weather;
    private OpenWeatherRepos openWeatherRepo;
    public void init(LatLng latLng){
        if(weather != null){
            return;
        }
        openWeatherRepo = OpenWeatherRepos.getInstance();
        openWeatherRepo.setLatLng(latLng);
        weather = openWeatherRepo.getWeather();
        Log.i("ViewModel","API Conn");
    }

    public LiveData<OpenWeather> getWeather(){
        return weather;
    }



}
