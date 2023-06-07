package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.API.ApiService;
import com.example.Adapter.MaterialTypeAdapter;
import com.example.Adapter.PrimaryUnitsAdapter;
import com.example.Models.MaterialTypes;
import com.example.Models.PrimaryUnits;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrimaryUnitsActivity extends AppCompatActivity {


    private RecyclerView rcv_primary_unit;
    private PrimaryUnitsAdapter primaryUnitsAdapter;
    private List<PrimaryUnits> primaryUnitsList;
    private ImageView btn_primary_unit_backToHome, imgView_Add_Primary_Unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_units);


        rcv_primary_unit = findViewById(R.id.rcv_primary_unit);
        btn_primary_unit_backToHome=findViewById(R.id.btn_primary_unit_backToHome);
        imgView_Add_Primary_Unit = findViewById(R.id.imgView_Add_Primary_Unit);
        loadData();
        onClick();

    }

    private void onClick() {
        btn_primary_unit_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });
        imgView_Add_Primary_Unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddPrimaryUnit.class));
                finish();
            }
        });
    }

    private void loadData() {
        primaryUnitsList = new ArrayList<>();
        primaryUnitsAdapter = new PrimaryUnitsAdapter(this, primaryUnitsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_primary_unit.setLayoutManager(linearLayoutManager);
        rcv_primary_unit.setAdapter(primaryUnitsAdapter);

        ApiService.api.GetPrimaryUnits().enqueue(new Callback<List<PrimaryUnits>>() {
            @Override
            public void onResponse(Call<List<PrimaryUnits>> call, Response<List<PrimaryUnits>> response) {
                if (response.body() != null){
                    primaryUnitsList.addAll(response.body());
                    primaryUnitsAdapter.setData(primaryUnitsList);
                }
            }
            @Override
            public void onFailure(Call<List<PrimaryUnits>> call, Throwable t) {

            }
        });
    }
}