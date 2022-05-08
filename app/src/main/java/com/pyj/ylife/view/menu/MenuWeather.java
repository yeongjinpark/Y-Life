package com.pyj.ylife.view.menu;

import static android.app.Activity.RESULT_OK;

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
    private MenuWeatherViewModel WViewModel;
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
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.i("getLatLon",location.getLatitude()+","+location.getLongitude());
                            WViewModel = new ViewModelProvider(requireActivity()).get(MenuWeatherViewModel.class);
                            WViewModel.init(new LatLng(location.getLatitude(),location.getLongitude()));
//                            WViewModel.init(new LatLng(latitude,longitude));
                            WViewModel.getWeather().observe(getViewLifecycleOwner(), new Observer<OpenWeather>() {
                                @Override
                                public void onChanged(OpenWeather openWeather) {
                                    Log.i("MenuWeather","API Conn");
                                    ow = WViewModel.getWeather().getValue();
                                    Log.i("MenuWeather",openWeather.toString());

                                    //tv_main.setText(String.valueOf(ow.getWeather().get(0).getMain()));
                                    tv_temp.setText(String.valueOf(ow.getMain().getTemp()));
                                    todayIcon(ow.getWeather().get(0).getIcon());
                                }
                            });
                        }
                    }
                });

        return view;
    }


    public void todayIcon(String weather){
        String imageURL = "https://openweathermap.org/img/wn/"+weather+"@2x.png";
        Glide.with(getContext()).load(imageURL).into(iv_weather_icon);

    }

}