package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.Users;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        Button loginbtn = findViewById(R.id.loginbtn);
        Button signup = findViewById(R.id.btn_login_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();

                if (username.length() ==0){
                    username.setError("Tên người dùng không được để trống!");
                }else if (pass.length() == 0){
                    password.setError("Mật khẩu không được để trống!");
                }else{
                    ApiService.api.Login(name,pass).enqueue(new Callback<Users>() {
                        @Override
                        public void onResponse(Call<Users> call, Response<Users> response) {
                            if (response.body() != null){
                                DataManager.currentUser = response.body();
                                Log.e("TAG", "currenUser: "+ DataManager.currentUser.getUsername());
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
    protected void moveToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}