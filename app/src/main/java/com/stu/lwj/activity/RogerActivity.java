package com.stu.lwj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stu.R;
import com.stu.lwj.util.Config;

public class RogerActivity extends AppCompatActivity {
    Button publish_bt, local_bt, tencent_bt, show_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lwj_roger_layout);
        init();
        publish_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RogerActivity.this, PublishActivity.class);
                startActivity(intent);
            }
        });

        local_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.useLocalhost();
                Toast.makeText(RogerActivity.this, "useLocalHost", Toast.LENGTH_SHORT).show();
            }
        });

        tencent_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.useTencent();
                Toast.makeText(RogerActivity.this, "useTencent", Toast.LENGTH_SHORT).show();
            }
        });


        show_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RogerActivity.this, ShowGoodActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        publish_bt = (Button) findViewById(R.id.button_to_PublishActivity);
        local_bt = (Button) findViewById(R.id.Local_button);
        tencent_bt = (Button) findViewById(R.id.Tencent_button);
        show_bt = (Button) findViewById(R.id.button_show);
    }
}
