package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.API.ApiService;
import com.example.Models.Users;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private Button btn_backToLogin, btn_signUp;
    private EditText edt_Username, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_backToLogin = findViewById(R.id.btn_backToLogin);
        btn_signUp = findViewById(R.id.btn_signUp);
        edt_Username = findViewById(R.id.edtUsernameDK);
        edt_password = findViewById(R.id.edtPasswordDK);


        btn_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });


        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_Username.getText().toString();
                String pass = edt_password.getText().toString();
                if (username.length() == 0 || pass.length() == 0) {
                    Toast.makeText(SignupActivity.this, "Các trường không được để trống!", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    ApiService.api.checkUsernameExist(username).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if (Boolean.TRUE.equals(response.body())) {
                                edt_Username.setError("Tên tài khoản đã tồn tại");
                            }else
                            {

                                Users user = new Users(username,pass,false);
                                Toast.makeText(SignupActivity.this,user.getUsername()+"   "+user.getUserPassword(), Toast.LENGTH_SHORT).show();
                                ApiService.api.PostUser(user).enqueue(new Callback<Users>() {
                                    @Override
                                    public void onResponse(Call<Users> call, Response<Users> response) {
                                        if (response.body()!=null) {

                                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                            finish();
                                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Không thành công", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Users> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                        }
                    });
                }
            }
        });

    }
}
