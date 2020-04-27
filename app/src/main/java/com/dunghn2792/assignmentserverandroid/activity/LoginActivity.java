package com.dunghn2792.assignmentserverandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dunghn2792.assignmentserverandroid.R;
import com.dunghn2792.assignmentserverandroid.api.API;
import com.dunghn2792.assignmentserverandroid.api.RetrofitClient;
import com.dunghn2792.assignmentserverandroid.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static List<User> userList = new ArrayList<>();
    EditText edtPassword, edtUsername;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setData();
        initView();
    }

    private void setData() {
        API apiConnect = RetrofitClient.getClient().create(API.class);
        apiConnect.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //hàm này là xử lý khi mà nhận được dữ liệu
                if (response.isSuccessful()) {
                    userList = response.body();
                    Log.d("ERRRR", "DATA: " + userList.size());
                    if (userList != null) {
                        Log.d("ERRRR", "server returned data");
                    } else {
                        Log.d("ERRRR", "Server returned an error");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                //hàm này là xử lý khi mà dữ liệu k nhận được
                if (t instanceof IOException) {

                    Toast.makeText(LoginActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                } else {
                    Toast.makeText(LoginActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }


    private void initView() {
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ERRR", "onClick: " + userList.size());
                for (User user : userList) {
                    if (edtUsername.getText().toString().equals(user.getEmail()) && edtPassword.getText().toString().equals(user.getPassword())) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", user.getId());
                        startActivity(intent);
                        Log.d("ERRR", "onClick: " + user.getEmail());
                    } else {
                        Toast.makeText(LoginActivity.this, "Tài khoản mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}