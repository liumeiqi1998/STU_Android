package com.stu.yyh.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stu.R;
import com.stu.yyh.ApplicationData;
import com.stu.yyh.User;
import com.stu.yyh.UserAction;
import com.stu.yyh.VerifyUtils;

import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {

    private Context mContext;
    private EditText register_account;
    private EditText register_password;
    private EditText second_password;


    private RadioGroup register_gender;
    private RadioButton register_male;
    private RadioButton register_female;

    private Button register_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyh_activity_register);

        mContext = this;

        initViews();


    }


    public void initViews() {

        register_account = (EditText) findViewById(R.id.register_account);
        register_password = (EditText) findViewById(R.id.register_password);
        second_password = (EditText) findViewById(R.id.register_second_password);

        register_gender = (RadioGroup) findViewById(R.id.register_gender);

        register_male = (RadioButton) findViewById(R.id.register_male);
        register_female = (RadioButton) findViewById(R.id.register_female);


        register_confirm = (Button) findViewById(R.id.register_confirm);



        register_confirm.setOnClickListener(confirmOnClickListener);



    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String account = register_account.getText().toString().trim();
            String password = register_password.getText().toString().trim();
            String sec_password = second_password.getText().toString().trim();

            int int_gender = register_gender.getCheckedRadioButtonId();

            if(int_gender == register_male.getId())
            {
                int_gender = 1;
            }
            else if(int_gender == register_female.getId())
            {
                int_gender = 0;
            }
            else
                int_gender = -1;

            if (account.equals("")) {
                Toast.makeText(RegisterActivity.this, "请填写账号", Toast.LENGTH_SHORT).show();
                register_account.requestFocus();
            } else if (password.equals("")) {
                Toast.makeText(RegisterActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();

            } else if (!VerifyUtils.matchAccount(account)) {
                Toast.makeText(RegisterActivity.this, "账号格式错误", Toast.LENGTH_SHORT).show();

                register_account.requestFocus();
            } else if (register_password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "密码格式错误", Toast.LENGTH_SHORT).show();

            } else if(!sec_password.equals(password))
            {
                Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
            }
            else if(int_gender == -1)
            {
                Toast.makeText(RegisterActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
            }
            else {
                tryRegister(account, password,int_gender);
            }
        }
    };


    private void tryRegister(final String account, final String password, final int gender) {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected void onPreExecute() {//开始时，直到
                super.onPreExecute();


            }

            @Override
            protected Integer doInBackground(Void... params) {//反应滞后性，不灵敏

                User user = new User();
                user.setAccount(account);
                user.setPassword(password);
                user.setGender(gender);

                UserAction.register(user, mContext);//验证登录


                return ApplicationData.getInstance().getUserInfo().getId();

            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

                finish();




            }
        }.executeOnExecutor(Executors.newCachedThreadPool());
//		}.execute();
    }



















}
