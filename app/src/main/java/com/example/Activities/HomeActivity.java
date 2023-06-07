package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.Materials;
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



    private List<Materials> materialsList;
    private int countIn, countOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        initView();
        loadData();
        onClick();

    }

    private void loadData() {
        txtUserName.setText(DataManager.currentUser.getUsername());
        materialsList = new ArrayList<>();
        ApiService.api.GetMaterials().enqueue(new Callback<List<Materials>>() {
            @Override
            public void onResponse(Call<List<Materials>> call, Response<List<Materials>> response) {
                if(response.body() != null){
                    materialsList.addAll(response.body());

                    for (int i = 0 ; i < materialsList.size() ; i++){
                        if (materialsList.get(i).isMaterialStatus()){
                            countIn++;
                        }
                        else countOut++;
                    }
                }
                textViewNumInProcess.setText(Integer.valueOf(countIn).toString());
                textViewNumOutProcess.setText(Integer.valueOf(countOut).toString());
            }

            @Override
            public void onFailure(Call<List<Materials>> call, Throwable t) {

            }
        });


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
    }
}