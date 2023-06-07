package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.API.ApiService;
import com.example.Models.PrimaryUnits;
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddResource extends AppCompatActivity {
    private ImageView btn_add_resource_backToMaterialType;
    private Button btn_create_resource;
    private EditText edt_add_resource_name, edt_add_resource_description;
    private Resources resources;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_resource);

        initView();
        onClick();
    }

    private void onClick() {
        btn_add_resource_backToMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResourceActivity.class));
                finish();
            }
        });

        btn_create_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resources = new Resources();

                resources.setResourceName(edt_add_resource_name.getText().toString());
                resources.setResourcelDescription(edt_add_resource_description.getText().toString());

                ApiService.api.PostResource(resources).enqueue(new Callback<Resources>() {
                    @Override
                    public void onResponse(Call<Resources> call, Response<Resources> response) {
                        if (response.body()!=null) {
                            startActivity(new Intent(getApplicationContext(),ResourceActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Resources> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void initView() {
        btn_add_resource_backToMaterialType = findViewById(R.id.btn_add_resource_backToMaterialType);
        btn_create_resource = findViewById(R.id.btn_create_resource);
        edt_add_resource_name = findViewById(R.id.edt_add_resource_name);
        edt_add_resource_description = findViewById(R.id.edt_add_resource_description);
    }
}
