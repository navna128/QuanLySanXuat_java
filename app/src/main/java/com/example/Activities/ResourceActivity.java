package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.API.ApiService;
import com.example.Adapter.MaterialTypeAdapter;
import com.example.Adapter.ResourceAdapter;
import com.example.Models.MaterialTypes;
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResourceActivity extends AppCompatActivity {

    private RecyclerView rcv_resource;
    private ResourceAdapter resourceAdapter;
    private List<Resources> resourcesList;
    private ImageView btn_resource_backToHome, imgView_Add_Resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);

        initView();
        loadData();
        onClick();
    }

    private void onClick() {
        btn_resource_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });

        imgView_Add_Resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddResource.class));
                finish();
            }
        });
    }

    private void loadData() {
        resourcesList = new ArrayList<>();
        resourceAdapter = new ResourceAdapter(this, resourcesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_resource.setLayoutManager(linearLayoutManager);
        rcv_resource.setAdapter(resourceAdapter);

        ApiService.api.GetResources().enqueue(new Callback<List<Resources>>() {
            @Override
            public void onResponse(Call<List<Resources>> call, Response<List<Resources>> response) {
                resourcesList.addAll(response.body());
                resourceAdapter.setData(resourcesList);
            }

            @Override
            public void onFailure(Call<List<Resources>> call, Throwable t) {

            }
        });
    }

    private void initView() {
        rcv_resource = findViewById(R.id.rcv_resource);
        btn_resource_backToHome = findViewById(R.id.btn_resource_backToHome);
        imgView_Add_Resource = findViewById(R.id.imgView_Add_Resource);
    }
}