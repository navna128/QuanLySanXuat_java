package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private TextView txtUserName, textViewNumInProcess, textViewNumOutProcess;
    private CardView CardViewMaterial;
    private CardView CardViewPrimaryUnit;
    private CardView CardViewMaterialType;
    private CardView CardViewProduct;
    private CardView CardViewResource;
    private Button btnReadMoreLeft, btnReadMoreRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        DataManager.process = 0;
        DataManager.countIn= 0;
        DataManager.countOut = 0;
        initView();
        loadData();
        onClick();

    }

    private void loadData() {
        txtUserName.setText(DataManager.currentUser.getUsername());

        ApiService.api.GetMaterialTypes().enqueue(new Callback<List<MaterialTypes>>() {
            @Override
            public void onResponse(Call<List<MaterialTypes>> call, Response<List<MaterialTypes>> response) {
                if (response.body() != null){

                    if (DataManager.materialTypesList.size()==0){
                        DataManager.materialTypesList.addAll(response.body());
                        Log.e("TAG", "onResponse: "+DataManager.materialTypesList.size() );
                    }
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
                    if(DataManager.primaryUnitsList.size() == 0){
                        DataManager.primaryUnitsList.addAll(response.body());
                    }
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
                    if(DataManager.productsList.size() == 0){
                        DataManager.productsList.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

        ApiService.api.GetResources().enqueue(new Callback<List<Resources>>() {
            @Override
            public void onResponse(Call<List<Resources>> call, Response<List<Resources>> response) {
                if (response.body() != null){
                    if (DataManager.resourcesList.size() == 0){
                        DataManager.resourcesList.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Resources>> call, Throwable t) {

            }
        });

        for (int i = 0 ; i < DataManager.materialsList.size() ; i++){
            if (DataManager.materialsList.get(i).isMaterialStatus()){
                DataManager.countIn++;
            }
            else DataManager.countOut++;
            textViewNumInProcess.setText(Integer.valueOf(DataManager.countIn).toString());
            textViewNumOutProcess.setText(Integer.valueOf(DataManager.countOut).toString());
        }

    }

    private void onClick() {
        CardViewMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                finish();
            }
        });
        CardViewMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                finish();
            }
        });
        CardViewPrimaryUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                finish();
            }
        });
        CardViewResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResourceActivity.class));
                finish();
            }
        });
        CardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                finish();
            }
        });
        btnReadMoreLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.process = 1;
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                finish();
            }
        });
        btnReadMoreRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.process = 2;
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        txtUserName = findViewById(R.id.txtUserName);
        CardViewMaterial = findViewById(R.id.CardViewMaterial);
        CardViewMaterialType = findViewById(R.id.CardViewMaterialType);
        CardViewPrimaryUnit = findViewById(R.id.CardViewPrimaryUnit);
        CardViewProduct = findViewById(R.id.CardViewProduct);
        CardViewResource = findViewById(R.id.CardViewResource);
        textViewNumInProcess = findViewById(R.id.textViewNumInProcess);
        textViewNumOutProcess = findViewById(R.id.textViewNumOutProcess);
        btnReadMoreLeft = findViewById(R.id.btnReadMoreLeft);
        btnReadMoreRight = findViewById(R.id.btnReadMoreRight);
    }
}