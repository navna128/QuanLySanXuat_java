package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.ApiService;
import com.example.Adapter.MaterialAdapter;
import com.example.Adapter.MaterialTypeAdapter;
import com.example.DataManager;
import com.example.Models.MaterialTypes;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialTypeActivity extends AppCompatActivity {

    private RecyclerView rcv_material_type;
    private MaterialTypeAdapter materialTypeAdapter;
    private List<MaterialTypes> materialTypesList;
    private ImageView imgView_Add_Material_Type, btn_mat_type_backToHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mateial_type);

        InitView();
        loadData();
        oncLick();





    }

    private void oncLick() {
        imgView_Add_Material_Type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddMaterialType.class));
                finish();
            }
        });
        btn_mat_type_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });
        materialTypeAdapter.setOnClickListener(new MaterialTypeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DataManager.selectedMaterialType = materialTypesList.get(pos);
                Log.e("mat click on", "onItemClick: "+ DataManager.selectedMaterialType.getTypeName() );
                startActivity(new Intent(getApplicationContext(),DetailMaterialTypeActivity.class));
                finish();
            }
        });
    }

    private void loadData() {
        materialTypesList = new ArrayList<>();
        materialTypeAdapter = new MaterialTypeAdapter(this, materialTypesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_material_type.setLayoutManager(linearLayoutManager);
        rcv_material_type.setAdapter(materialTypeAdapter);


        ApiService.api.GetMaterialTypes().enqueue(new Callback<List<MaterialTypes>>() {
            @Override
            public void onResponse(Call<List<MaterialTypes>> call, Response<List<MaterialTypes>> response) {
                materialTypesList.addAll(response.body());
                materialTypeAdapter.setData(materialTypesList);
            }

            @Override
            public void onFailure(Call<List<MaterialTypes>> call, Throwable t) {

            }
        });
    }

    private void InitView() {
        rcv_material_type = findViewById(R.id.rcv_material_type);
        imgView_Add_Material_Type = findViewById(R.id.imgView_Add_Material_Type);
        btn_mat_type_backToHome = findViewById(R.id.btn_mat_type_backToHome);
    }
}
