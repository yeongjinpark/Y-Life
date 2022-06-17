package com.pyj.ylife.view.menu;

import
        android.annotation.SuppressLint;
import android.location.Address;
import android.location.Location;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.pyj.ylife.R;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;


public class MenuMap extends Fragment implements
                                        GoogleMap.OnMyLocationButtonClickListener,
                                        GoogleMap.OnMyLocationClickListener
{

    private View view;

    //map
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationClient;
    private Geocoder geocoder;
    Button btn_search;
    EditText editText;


    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_map, container, false);
        geocoder = new Geocoder(getContext());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        editText = (EditText) view.findViewById(R.id.editText);
        btn_search=(Button) view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAddress();
            }
        });


        //지도 API를 사용하기위해 객체 선언 하는 부분
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        mapFragment = SupportMapFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.map,mapFragment)
                .commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) { //지도를 사용할 준비가 되었고, 지도API를 사용하는곳
                Log.i("MyLocTest","지도 준비됨");
                map = googleMap;

                getNowLocation();

                map.setMyLocationEnabled(true);
                onMyLocationButtonClick();
            }
        });
        return view;
    }

    //현재위치를 좌표로 받아오는 함수
    @SuppressLint("MissingPermission")
    private void getNowLocation(){
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener( getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()) , 18));
                        }
                    }
                });
    }
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }

    public void getAddress(){
        List<Address> addressList = null;
        String str=editText.getText().toString();
        try {
            // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
            addressList = geocoder.getFromLocationName(
                    str, // 주소
                    10); // 최대 검색 결과 개수
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(addressList.get(0).toString());
        // 콤마를 기준으로 split
        String []splitStr = addressList.get(0).toString().split(",");
        String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
        System.out.println(address);

        String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
        String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
        System.out.println(latitude);
        System.out.println(longitude);

        // 좌표(위도, 경도) 생성
        LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        // 마커 생성
        MarkerOptions mOptions2 = new MarkerOptions();
        mOptions2.title("search result");
        mOptions2.snippet(address);
        mOptions2.position(point);
        // 마커 추가
        map.addMarker(mOptions2);
        // 해당 좌표로 화면 줌
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
    }
}