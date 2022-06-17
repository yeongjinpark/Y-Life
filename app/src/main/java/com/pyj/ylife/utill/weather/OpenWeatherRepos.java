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

    //Retrofit2 를 사용하기위해 Retrofit.Builder()를 사용해서 인스턴스 생성
    public MutableLiveData<OpenWeather> getWeather(){
        //Retrofit을 사용하기 위해 WeatherAPI url설정
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        //OpenWeatherAPI 객체 구현
        openAPI = retrofit.create(OpenWeatherAPI.class);

        openWeather = new OpenWeather();
        MutableLiveData<OpenWeather> data = new MutableLiveData<>();
        //실질적으로 API를 사용하는곳
        //조회할려는 위치 정보, API 사용자 고유번호를 입력하고,
        //정보를 얻어와서 data에 날씨정보를 담아둔다.
        callWeatherAPI(data);
        return data;
    }

    private void callWeatherAPI(MutableLiveData<OpenWeather> data){
        Log.i("LatLong", String.valueOf(latLng.latitude));
        //위치정보와 API 번호를 이용해서 'OpenWeatherAPI'를 통해 날씨정보를 조회
        Call<OpenWeather> call = openAPI.getWeather(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude),"0b93eae510fc7849431559f5bace3e8e");
        //날씨 API로 부터 받아온 정보를 정상적으로 받아왔으면 OnResponse, 받아오는데 실패 했으면 OnFailure
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
