package com.pyj.ylife.view.menu;

import static android.app.Activity.RESULT_OK;

import static java.lang.String.format;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.pyj.ylife.R;
import com.pyj.ylife.dto.OpenWeather;
import com.pyj.ylife.dto.Weather;
import com.pyj.ylife.model.MenuWeatherViewModel;

import org.w3c.dom.Text;

import java.util.Objects;


public class MenuWeather extends Fragment {
    View view;
    private MenuWeatherViewModel WViewModel; //날씨 정보를 담는곳
    private OpenWeather ow;

    private FusedLocationProviderClient fusedLocationClient;

    private TextView tv_temp;
    private TextView tv_main;
    private ImageView iv_weather_icon;

    @SuppressLint("MissingPermission")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_weather,container,false);
        tv_main = view.findViewById(R.id.tv_main);
        tv_temp = view.findViewById(R.id.tv_temp);
        iv_weather_icon = view.findViewById(R.id.iv_weather_icon);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) { //location은 현재 위치 좌표값
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.i("getLatLon",location.getLatitude()+","+location.getLongitude());

                            //ViewModel 객체를 생성하고 선언
                            WViewModel = new ViewModelProvider(requireActivity()).get(MenuWeatherViewModel.class);
                            //gps로 부터 받아온 좌표값 location을 이용하여 API 설정
                            WViewModel.init(new LatLng(location.getLatitude(),location.getLongitude()));
//                            WViewModel.init(new LatLng(latitude,longitude));
                            //날씨정보를 가져오는 곳
                            WViewModel.getWeather().observe(getViewLifecycleOwner(), new Observer<OpenWeather>() {
                                @Override
                                public void onChanged(OpenWeather openWeather) {//가져오는데 성공했을때
                                    Log.i("MenuWeather","API Conn");
                                    ow = WViewModel.getWeather().getValue();
                                    Log.i("MenuWeather",openWeather.toString());

                                    //MAIN 날씨
                                    String main= String.valueOf(ow.getWeather().get(0).getMain());
                                    //description
                                    String description=String.valueOf(ow.getWeather().get(0).getDescription());


                                    //tv_main.setText(String.valueOf(ow.getWeather().get(0).getMain()));
                                    tv_temp.setText(String.valueOf(Math.floor(ow.getMain().getTemp()-273.15)+" ºC"));
                                    todayIcon(ow.getWeather().get(0).getIcon());
                                    Log.i("weathericon",ow.getWeather().get(0).getIcon().toString());
                                }
                            });
                        }
                    }
                });

        return view;
    }

    //현재 날씨에 맞는 아이콘을 사용(OpenWeatherAPI공식 아이콘)
    //Glide 라이브러리를 사용하여 이미지 사용
    public void todayIcon(String weather){
        String imageURL = "https://openweathermap.org/img/wn/"+weather+"@2x.png";
        Glide.with(getContext()).load(imageURL).into(iv_weather_icon);

    }

}