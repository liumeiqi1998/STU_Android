package com.stu.yyh;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/3/28.
 */

//数据模型层，全局类，统一处理模型曾与数据层交互，方便可移植性，惊醒模块开发
public class Model {
    private Context mContext;
    private ExecutorService executors= Executors.newCachedThreadPool();//全局线程池对象
    private VolleySingleton volleyRequest;
    private static String name;

    private static Model model=new Model();
    public void setName(String name){//保存账户
        this.name=name;
    }
    public String getName(){
        return name;
    }
    //私有化构造，
    private Model(){

    }
    //获取单例对象
    public static Model getInstance(){
        return model;
    }
    //初始化方法
    public void init(Context context){
        mContext=context;
    }
    public ExecutorService getGlobalThreadPool(){//获取全局线程池对象
        return executors;
    }//全局线程

    public VolleySingleton getVolleyRequest(){//全局类请求
        return new VolleySingleton(mContext);
    }



}