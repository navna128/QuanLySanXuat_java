package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.ApiService;
import com.example.Adapter.MaterialAdapter;
import com.example.Models.Materials;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialActivity extends AppCompatActivity {
    private RecyclerView rcv_material;
    private MaterialAdapter materialAdapter;
    private List<Materials> materials;
    private ImageView btn_mat_backToHome, imgView_Add_Material;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);


        initView();
        setUpView();
        loadData();
        onClick();
    }

    private void onClick() {
        imgView_Add_Material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddMaterialActivity.class));
                finish();
            }
        });
        btn_mat_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });
    }

    private void loadData() {

        ApiService.api.GetMaterials().enqueue(new Callback<List<Materials>>() {
            @Override
            public void onResponse(Call<List<Materials>> call, Response<List<Materials>> response) {
                materials.addAll(response.body());
                materialAdapter.setData(materials);
            }

            @Override
            public void onFailure(Call<List<Materials>> call, Throwable t) {

            }
        });

        /*ApiService.api.GetMaterials().enqueue(new Callback<List<Materials>>() {
            @Override
            public void onResponse(Call<List<Materials>> call, Response<List<Materials>> response) {
                if (response.body() != null){
                    Toast.makeText(MaterialActivity.this,response.body().size() , Toast.LENGTH_SHORT).show();
                    if (response.body().size() > 0){
                        materials.addAll(response.body());
                        materialAdapter.setData(materials);
                    }else {
                        Toast.makeText(MaterialActivity.this, "Không có vật liệu", Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(MaterialActivity.this, "Không có vật liệu", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Materials>> call, Throwable t) {
                Toast.makeText(MaterialActivity.this, "Không có vật liệu", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void setUpView() {
            materials = new ArrayList<>();
            materialAdapter = new MaterialAdapter(this, materials);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcv_material.setLayoutManager(linearLayoutManager);
            rcv_material.setAdapter(materialAdapter);
    }

    private void initView() {
        imgView_Add_Material = findViewById(R.id.imgView_Add_Material);
        rcv_material = findViewById(R.id.rcv_material);
        btn_mat_backToHome = findViewById(R.id.btn_mat_backToHome);
    }
}
