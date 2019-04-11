package com.stu.lwj.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void show(Context context,String showText){
        Toast.makeText(context, showText, Toast.LENGTH_SHORT).show();
    }
}
