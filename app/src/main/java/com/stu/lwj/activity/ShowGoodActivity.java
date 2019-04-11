package com.stu.lwj.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import com.stu.R;
import com.stu.lwj.bean.GoodItem;
import com.stu.lwj.util.Config;
import com.stu.lwj.util.OkhttpUtil;
import com.stu.lwj.util.ToastUtil;

public class ShowGoodActivity extends AppCompatActivity {
    ListView listView;
    BaseAdapter baseAdapter;
    TextView title, oldPrice;
    List<GoodItem> list = new ArrayList<>();
    String URL = Config.getWebUrl()+"/PullGoods";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lwj_show_layout);

        NetWorkThread netWorkThread = new NetWorkThread();
        netWorkThread.start();
        try {
            netWorkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.listview);
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
                LayoutInflater inflater = ShowGoodActivity.this.getLayoutInflater();
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
                title = (TextView) findViewById(R.id.good_title);
                oldPrice = (TextView) findViewById(R.id.good_old_price);
                String title_str = title.getText().toString();
                String oldPrice_str = oldPrice.getText().toString();
                ToastUtil.show(ShowGoodActivity.this,
                        "{position:"+position+", title:"+title_str+", oldPrice:"+oldPrice_str+"}");
            }
        });
    }
    class NetWorkThread extends Thread{
        @Override
        public void run() {
            try {
                Response response = OkhttpUtil.syncGet(URL);
                String json = response.body().string();
                Log.i("Roger", json);
                if (json.equals("")) {

                } else if(!json.equals("placeHolder")) {
                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
                    for (JsonElement item : jsonArray) {
                        GoodItem goodItem = gson.fromJson(item, GoodItem.class);
                        list.add(goodItem);
                        Log.i("Roger", goodItem.toString());
                    }
                    Log.i("Item num", String.valueOf(list.size()));
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
