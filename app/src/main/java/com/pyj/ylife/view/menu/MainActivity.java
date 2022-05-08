package com.pyj.ylife.view.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pyj.ylife.R;
import com.pyj.ylife.utill.BackPressHandler;


public class MainActivity  extends AppCompatActivity {
    // 뒤로가기 버튼 작업
    private BackPressHandler bp;

    //바텀네비&각 페이지
    private BottomNavigationView bottomNavi;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MenuMap menu_map;
    private Phone phone;
    private MenuWeather menuWeather;
    private MenuKiosk menuKiosk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bp = new BackPressHandler(this);
        menu_map = new MenuMap();
        phone = new Phone();
        menuWeather = new MenuWeather();
        menuKiosk = new MenuKiosk();
        bottomNavi = findViewById(R.id.bottom_main);

        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_phone:
                        setFrag(0);
                        break;
                    case R.id.navi_map:
                        setFrag(1);
                        break;
                    case R.id.navi_wheather:
                        setFrag(2);
                        break;
                    case R.id.navi_kiosk:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        setFrag(0);

    }



    private void setFrag(int i){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (i){
            case 0:
                ft.replace(R.id.frame_main,phone).commit();
                break;
            case 1:
                ft.replace(R.id.frame_main, menu_map).commit();
                break;
            case 2:
                ft.replace(R.id.frame_main, menuWeather).commit();
                break;
            case 3:
                ft.replace(R.id.frame_main, menuKiosk).commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        bp.onBackPressed(3000);
    }
}