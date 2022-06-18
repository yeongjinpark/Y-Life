package com.pyj.ylife.view.menu.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pyj.ylife.R;

public class Call extends Activity {
    Button btn_son;
    Button btn_dal;
    Button btn_emergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        btn_son=(Button)findViewById(R.id.son);
        btn_dal=(Button)findViewById(R.id.dal);
        btn_emergency=(Button)findViewById(R.id.emrgency);

        btn_son.setOnClickListener(new View.OnClickListener() { //버튼클릭-> 01011111111로 전화연결
            @Override
            public void onClick(View view) {
                Intent mlntent_son= new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/01011111111"));
                startActivity(mlntent_son);
            }
        });

        btn_dal.setOnClickListener(new View.OnClickListener() { //버튼클릭-> 01012341234로 전화연결
            @Override
            public void onClick(View view) {
                Intent mlntent_dal= new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/01012341234"));
                startActivity(mlntent_dal);
            }
        });

        btn_emergency.setOnClickListener(new View.OnClickListener() { //버튼클릭-> 119전화연결
            @Override
            public void onClick(View view) {
                Intent mlntent_emergency= new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/119"));
                startActivity(mlntent_emergency);
            }
        });

    }
}