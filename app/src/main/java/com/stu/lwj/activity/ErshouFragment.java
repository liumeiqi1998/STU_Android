package com.stu.lwj.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.stu.R;
import com.stu.lwj.bean.GoodItem;
import com.stu.lwj.util.Config;
import com.stu.lwj.util.OkhttpUtil;
import com.stu.lwj.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ErshouFragment extends Fragment {
    ListView listView;
    BaseAdapter baseAdapter;
    TextView title, oldPrice;
    List<GoodItem> list = new ArrayList<>();
    String URL = Config.getWebUrl()+"/PullGoods";
    private Context context;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkhttpUtil.aysnGet(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Roger::Failure", e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = "[]";
                handler.sendMessage(msg);
                if (response.body() != null) {
                    msg.obj = response.body().string();
                }
                Log.i("Roger", (String) msg.obj);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        if(view == null){
            view = View.inflate(context, R.layout.lwj_show_layout, null);
        }
        return view;
    }

    private void listSet(){
        listView = view.findViewById(R.id.listview);
        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public GoodItem getItem(int position) {
                return list.get(position);
            }
            @Override
            public long getItemId(int position) {
                return 0;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = ErshouFragment.this.getLayoutInflater();
                View view;

                if (convertView==null) {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.lwj_good_item, null);
                }else{
                    view = convertView;
                    Log.i("info","有缓存，不需要重新生成"+position);
                }
                title =  view.findViewById(R.id.good_title);    //找到Textviewname
                title.setText(list.get(position).getTitle());   //设置参数

                oldPrice = view.findViewById(R.id.good_old_price);  //找到Textviewage
                oldPrice.setText(String.valueOf(list.get(position).getOldPrice())); //设置参数
                return view;
            }
        };
        listView.setAdapter(baseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title =  view.findViewById(R.id.good_title);
                oldPrice =  view.findViewById(R.id.good_old_price);
                String title_str = title.getText().toString();
                String oldPrice_str = oldPrice.getText().toString();
                ToastUtil.show(context, "{position:"+position+", title:"+title_str+", "+
                                "oldPrice:"+oldPrice_str+"}");
            }
        });
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                String json = (String) msg.obj;
                if(!json.equals("[]")) {
                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
                    for (JsonElement item : jsonArray) {
                        GoodItem goodItem = gson.fromJson(item, GoodItem.class);
                        list.add(goodItem);
                        Log.i("Roger", goodItem.toString());
                    }
                    Log.i("Item num", String.valueOf(list.size()));
                    listSet();
                }
            }
        }
    };
}
