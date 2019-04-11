package com.stu.yyh;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/27.
 */

public class UserAction {

//    static String string_front = "http://39.108.116.192:8080/MyServlet";
    static final String string_front = "http://39.108.116.192:8080/YYH_Servlet/";
    //10.21.138.33
    //192.168.43.197
    //39.108.116.192

    //登录访问服务器
    public static void loginVerify(final User user, final Context mContext) {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {

                final String url = string_front+"LoginServlet";
                String tag = "Login";//请求标记
                Model.getInstance().init(mContext);
                RequestQueue requestQueue = Model.getInstance().getVolleyRequest().getRequestQueue();
                requestQueue.cancelAll(tag);



                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{


                                    JSONObject jsonObject = new JSONObject(response);
                                    User u = new User();
                                    u.setId(Integer.parseInt(jsonObject.get("Id").toString()));

                                    u.setAccount(jsonObject.get("Account").toString());
                                    u.setGender(Integer.parseInt(jsonObject.get("Gender").toString()));

//                                    Toast.makeText(mContext,String.valueOf(u.getId()),Toast.LENGTH_LONG).show();
//                                    Toast.makeText(mContext,jsonObject.get("Id").toString(),Toast.LENGTH_LONG).show();
                                    ApplicationData.getInstance().setmUser(u);


                                }catch (JSONException e) {
                                    //做自己的请求异常操作，如Toast提示（“无网络连接”等）
                                    Toast.makeText(mContext,"login1:"+e.toString(),Toast.LENGTH_LONG).show();

                                }
                                catch(Exception e){
                                    Toast.makeText(mContext,"login12:"+e.toString(),Toast.LENGTH_LONG).show();
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", error.getMessage(), error);
                        Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("AccountNumber", user.getAccount());
                        params.put("password", user.getPassword());
                        return params;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });

    }



    public static void register(final User user, final Context registerActivity) {

        //具体的登录逻辑处理，设计联网操作，开线程
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //请求地址
                String url = string_front + "RegisterServlet";
                String tag = "Register";//请求标记
                Model.getInstance().init(registerActivity);
                //取得请求队列
                RequestQueue requestQueue = Model.getInstance().getVolleyRequest().getRequestQueue();
                //防止重复请求，所以先取消tag标识的请求队列
                requestQueue.cancelAll(tag);
                ;
                //创建StringRequest，定义字符串请求的请求方式为POST(省略第一个参数会默认为GET方式)
                final StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = null;

                                    jsonObject = (JSONObject) new JSONObject(response).get("params");

                                    String result = jsonObject.getString("Result");
                                    if (result.equals("success")) {
                                        Toast.makeText(registerActivity,"register success",Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(registerActivity,"register fail",Toast.LENGTH_LONG).show();
                                    }
//                                    StepPhoto.setRegisterInfo(mReceived, true);//通知账号注册情况
                                } catch (JSONException e) {

                                    Toast.makeText(registerActivity,"register:"+e.toString(),Toast.LENGTH_LONG).show();
                                    //做自己的请求异常操作，如Toast提示（“无网络连接”等）
//                                    Log.e("TAG", e.getMessage(), e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //做自己的响应错误操作，如Toast提示（“请稍后重试”等）
                                Log.e("TAG", error.getMessage(), error);
                            }
                        }) {//这里要大概
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("AccountNumber", user.getAccount());
                        params.put("mPassword", user.getPassword());
                        params.put("mGender", String.valueOf(user.getGender()));//转换为字符型
                        return params;
                    }
                };
                //设置Tag标签
                request.setTag(tag);
                //将请求添加到队列中
                requestQueue.add(request);
            }
        });

    }

}
