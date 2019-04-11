package com.stu.yyh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.stu.yyh.activity.LoginActivity;
import com.stu.yyh.activity.RegisterActivity;
import com.stu.R;
import com.stu.yyh.ApplicationData;

/**
 * Created by Administrator on 2019/4/4.
 */


public class SettingFragment extends Fragment {

    private View mBaseView;
    private Button setting_login;

    private ScrollView setting_scrollview;

    private Button setting_register;

    private TextView setting_textview_id;
    private TextView setting_textview_account;
    private TextView setting_textview_gender;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBaseView = inflater.inflate(R.layout.yyh_fragment_setting, null);
        findView();
        init();
        return mBaseView;
    }



    private void findView() {

        setting_login = mBaseView.findViewById(R.id.setting_login);
        setting_scrollview = mBaseView.findViewById(R.id.setting_scrollview);
        setting_register = mBaseView.findViewById(R.id.setting_register);

        setting_textview_id = mBaseView.findViewById(R.id.setting_id);
        setting_textview_account  = mBaseView.findViewById(R.id.setting_account);
        setting_textview_gender = mBaseView.findViewById(R.id.setting_gender);


        if(ApplicationData.getInstance().getUserInfo().getId()!=-1){
            setting_login.setVisibility(View.GONE);
            setting_register.setVisibility(View.GONE);

            setting_scrollview.setVisibility(View.VISIBLE);
            setting_textview_id.setText(""+ApplicationData.getInstance().getUserInfo().getId());
            setting_textview_account.setText(""+ApplicationData.getInstance().getUserInfo().getAccount());


            if(ApplicationData.getInstance().getUserInfo().getGender()==0)
                setting_textview_gender.setText("女");
            else
                setting_textview_gender.setText("男");


        }


    }


    private void init() {

        setting_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        setting_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });






    }




}

