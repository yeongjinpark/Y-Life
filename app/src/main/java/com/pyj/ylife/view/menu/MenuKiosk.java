package com.pyj.ylife.view.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pyj.ylife.R;
import com.pyj.ylife.view.menu.phone.Kioskvideo;
import com.pyj.ylife.view.menu.phone.music;
import com.pyj.ylife.view.menu.phone.stretching;

public class MenuKiosk extends Fragment {
    private View view;
    private ImageView iv_phone;
    private ImageView iv_Msg;
    private ImageView iv_Camera;
    private ImageView iv_Album;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_kiosk,container,false);
        init();

        iv_phone.setOnClickListener(onClickListener);
        iv_Msg.setOnClickListener(onClickListener);
        iv_Camera.setOnClickListener(onClickListener);
        iv_Album.setOnClickListener(onClickListener);
        return view;
    }


    private void init(){
        iv_phone = (ImageView)view.findViewById(R.id.iv_phone);
        iv_Msg = (ImageView)view.findViewById(R.id.iv_Msg);
        iv_Camera = (ImageView)view.findViewById(R.id.iv_Camera);
        iv_Album = (ImageView)view.findViewById(R.id.iv_Album);
    }


    Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_phone:
                    myStartActivity(Kioskvideo.class);
                    break;
                case R.id.iv_Msg:
                    myStartActivity(music.class);
                    break;

                case R.id.iv_Camera:
                    myStartActivity(stretching.class);
                    break;

                case R.id.iv_Album:
                    Intent mlntent_dementia= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sejong.go.kr/health/sub05_03_08.do"));
                    startActivity(mlntent_dementia);
                    break;
            }
        }
    };


    private void myStartActivity(Class c){
        Intent intent = new Intent(getContext(),c);
        startActivity(intent);
    }
}