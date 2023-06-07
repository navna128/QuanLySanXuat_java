package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.ApiService;
import com.example.Adapter.MaterialAdapter;
import com.example.DataManager;
import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
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
    private TextView txtUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        materialAdapter.setOnClickListener(new MaterialAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DataManager.selectedMaterial = materials.get(pos);
                Log.e("mat click on", "onItemClick: "+ DataManager.selectedMaterial.getMaterialDescription() );
                startActivity(new Intent(getApplicationContext(),DetailMaterialActivity.class));
                finish();
            }
        });
    }


    private void loadData() {

        ApiService.api.GetMaterials().enqueue(new Callback<List<Materials>>() {
            @Override
            public void onResponse(Call<List<Materials>> call, Response<List<Materials>> response) {
                if (response.body() != null){
                    materials.addAll(response.body());
                    materialAdapter.setData(materials);
                }
            }
            @Override
            public void onFailure(Call<List<Materials>> call, Throwable t) {
            }
        });
        ApiService.api.GetMaterialTypes().enqueue(new Callback<List<MaterialTypes>>() {
            @Override
            public void onResponse(Call<List<MaterialTypes>> call, Response<List<MaterialTypes>> response) {
                if (response.body() != null){
                    DataManager.materialTypesList.clear();
                    DataManager.materialTypesList.addAll(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<MaterialTypes>> call, Throwable t) {

            }
        });

        ApiService.api.GetPrimaryUnits().enqueue(new Callback<List<PrimaryUnits>>() {
            @Override
            public void onResponse(Call<List<PrimaryUnits>> call, Response<List<PrimaryUnits>> response) {
                if (response.body() != null){
                    DataManager.primaryUnitsList.clear();
                    DataManager.primaryUnitsList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PrimaryUnits>> call, Throwable t) {

            }
        });

        ApiService.api.GetProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.body() != null){
                    DataManager.productsList.clear();
                    DataManager.productsList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });


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
        txtUserName = findViewById(R.id.txtUserName);
    }
}
