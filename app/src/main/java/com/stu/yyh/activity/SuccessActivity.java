package com.stu.yyh.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.stu.lwj.activity.ErshouFragment;
import com.stu.yyh.fragment.DainaFragment;
import com.stu.yyh.fragment.ChatFragment;
import com.stu.yyh.fragment.PindanFragment;
import com.stu.yyh.fragment.SettingFragment;
import com.stu.R;
import com.stu.yyh.UserAction;


//主界面，没有登录也能进入
public class SuccessActivity extends FragmentActivity {

    protected static final String TAG = "MainActivity";
    private Context mContext;
    private ImageButton ershou, daina, pindan, chat, setting;
    private View currentButton;
    private ErshouFragment ershouFragment;
    private DainaFragment dainaFragment;
    private PindanFragment pindanFragment;
    private ChatFragment chatFragment;
    private SettingFragment settingFragment;

    private PopupWindow mPopupWindow;
    private LinearLayout buttomBarGroup;

    private UserAction userAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyh_activity_success);
        mContext = this;
        findView();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //默认二手界面
        ershou.performClick();
    }

    private void findView() {
        buttomBarGroup = (LinearLayout) findViewById(R.id.buttom_bar_group);
        ershou = (ImageButton) findViewById(R.id.ershou);
        daina = (ImageButton) findViewById(R.id.daina);
        pindan = (ImageButton) findViewById(R.id.pindan);
        chat = (ImageButton) findViewById(R.id.chat);
        setting = (ImageButton) findViewById(R.id.setting);
    }

    private void init() {
        ershou.setOnClickListener(ershouOnClickListener);
        daina.setOnClickListener(dainaOnClickListener);
        pindan.setOnClickListener(pindanOnClickListener);
        chat.setOnClickListener(chatOnClickListener);
        setting.setOnClickListener(settingOnClickListener);
    }

    //以下四个触发一个以后，分别进入各自的界面
    private View.OnClickListener ershouOnClickListener = new View.OnClickListener() {//消息页面
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (ershouFragment == null) {
                ershouFragment = new ErshouFragment();
            }
            ft.replace(R.id.fl_content, ershouFragment);//切换页面
            ft.commit();
            setButton(v);
        }
    };

    private View.OnClickListener dainaOnClickListener = new View.OnClickListener() {//消息页面
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (dainaFragment == null) {
                dainaFragment = new DainaFragment();
            }
            ft.replace(R.id.fl_content, dainaFragment);//切换页面
            ft.commit();
            setButton(v);
        }
    };

    private View.OnClickListener pindanOnClickListener = new View.OnClickListener() {//消息页面
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (pindanFragment == null) {
                pindanFragment = new PindanFragment();
            }
            ft.replace(R.id.fl_content, pindanFragment);//切换页面
            ft.commit();
            setButton(v);
        }
    };

    private View.OnClickListener chatOnClickListener = new View.OnClickListener() {//消息页面
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (chatFragment == null) {
                chatFragment = new ChatFragment();
            }
            ft.replace(R.id.fl_content, chatFragment);//切换页面
            ft.commit();
            setButton(v);
        }
    };

    private View.OnClickListener settingOnClickListener = new View.OnClickListener() {//消息页面
        @Override
        public void onClick(View v) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (settingFragment == null) {
                settingFragment = new SettingFragment();
            }
            ft.replace(R.id.fl_content, settingFragment);//切换页面
            ft.commit();
            setButton(v);
        }
    };


    private void setButton(View v) {//切换显示底部按钮
        if (currentButton != null && currentButton.getId() != v.getId()) {
            currentButton.setEnabled(true);
        }
        v.setEnabled(false);
        currentButton = v;
    }


}
