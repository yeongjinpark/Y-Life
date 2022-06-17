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
        //싱글톤 패턴 : 날씨 정보 API를 가져오는 객체 인스턴스 선언
        openWeatherRepo = OpenWeatherRepos.getInstance();
        //위치 데이터 넣어주는곳
        openWeatherRepo.setLatLng(latLng);
        //'OpenWeather' 클래스는 날씨 데이터를 담아 두는 클래스
        //날씨 API를 통해 날씨 정보를 가져와서 weather 변수에 담는 곳
        weather = openWeatherRepo.getWeather();
        Log.i("ViewModel","API Conn");
    }

    public LiveData<OpenWeather> getWeather(){
        return weather;
    }



}
