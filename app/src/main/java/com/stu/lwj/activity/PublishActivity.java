package com.stu.lwj.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;

import com.stu.R;
import com.stu.lwj.bean.GoodItem;
import com.stu.lwj.util.Config;
import com.stu.lwj.util.FormatUtil;
import com.stu.lwj.util.OkhttpUtil;
import com.stu.lwj.util.ToastUtil;

/**
 * Created by Roger on 2018/10/11.
 */

public class PublishActivity extends AppCompatActivity {
    private ArrayList<String> list;
    //private Spinner mySpinner;
    private ArrayAdapter<String> adapter;
    private EditText    titleEditText,
                        oldPriceEditText,
                        priceEditText,
                        introductionEditText;
    private Button publish_button,
                    photo_button;
    private ImageView image;
    private Bitmap bitmap;
    private String photoHexString = "";
    private static final int PHOTO_REQUEST_CAREMA = 1;  // 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2; // 从相册中选择

    //private static final int PHOTO_REQUEST_CUT = 3;     // 结果

    private static String URL_Publish = Config.getWebUrl() + "Publish";
    private static String item_select = "手机";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lwj_publish_layout);
        init();

        publish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = true;
                String title = titleEditText.getText().toString();
                String oldPrice = oldPriceEditText.getText().toString();
                String price = priceEditText.getText().toString();
                String introduction = introductionEditText.getText().toString();

                if (title.isEmpty() || oldPrice.isEmpty() || price.isEmpty() || introduction.isEmpty()) {
                    ok = false;
                    ToastUtil.show(PublishActivity.this, "含有未填项，不能发布");
                } if(photoHexString.isEmpty()){
                    ToastUtil.show(PublishActivity.this, "未上传图片");
                    ok = false;
                }
                if (ok) { //可以发布
                    ToastUtil.show(PublishActivity.this, "即将发布");

                    Gson gson = new Gson();
                    GoodItem goodItem = new GoodItem(title, Float.parseFloat(oldPrice),
                            Float.parseFloat(price), introduction, photoHexString);
                    String json = gson.toJson(goodItem);
                    Log.i("json:length",json.length()+"");
                    OkhttpUtil.postJson(URL_Publish, json, OkhttpUtil.getDeafultCallback());
                }
            }
        });

        photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PublishActivity.this, android.R.style.Theme_Holo_Light_Dialog);
                builder.setTitle("选择上传方式");
                final String[] choices = {"拍照上传", "从相册选取"};
                builder.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:     //选择了 拍照上传
                                Intent intent0 = new Intent("android.media.action.IMAGE_CAPTURE");
                                startActivityForResult(intent0, PHOTO_REQUEST_CAREMA);
                                break;
                            case 1:     //选择了 从相册选择
                                Intent intent1 = new Intent(Intent.ACTION_PICK);
                                intent1.setType("image/*");
                                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

        /*下拉列表
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                item_select = Config.getCATEGORY()[arg2];
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY || requestCode == PHOTO_REQUEST_CAREMA) {
            if (data != null) {
                bitmap = data.getParcelableExtra("data");
                byte[] bytes = FormatUtil.bitmapToByteArray(bitmap, 20);

                photoHexString = FormatUtil.byteArrayToHexString(bytes);
                image.setImageBitmap(bitmap);
                ToastUtil.show(PublishActivity.this, bytes.length+"");
            }
        }
    }

    private void init(){
        /*
        String[] a = Config.getCATEGORY().clone();
        list = new ArrayList<String>();
        for (String i:a){
            list.add(i);
        }
        */
        image = (ImageView) findViewById(R.id.imageView);

        titleEditText           = (EditText) findViewById(R.id.editText);
        oldPriceEditText        = (EditText) findViewById(R.id.editText2);
        priceEditText        = (EditText) findViewById(R.id.editText3);
        introductionEditText    = (EditText) findViewById(R.id.editText4);

        publish_button  = (Button)  findViewById(R.id.publish_button);
        photo_button    = (Button)  findViewById(R.id.photo_button);
        /*
        mySpinner       = (Spinner) findViewById(R.id.spinner);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        */
    }
}

