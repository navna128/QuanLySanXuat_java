package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
    private ImageView btn_mat_backToHome, imgView_Add_Material;
    private EditText edt_search_mat;
    private List<Materials> allMaterials;
    private List<Materials> InProcessMaterials;
    private List<Materials> OutProcessMaterials;

    private List<Materials> All = new ArrayList<>();
    private List<Materials> InProcess = new ArrayList<>();
    private List<Materials> OutProcess = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_material);

        All=DataManager.materialsList;
        InProcess = DataManager.materialsInProcessList;
        OutProcess = DataManager.materialsOutProcessList;
        initView();
        setUpView();
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
                Log.e("TAG", "onItemClick: " + All.size());
                if (DataManager.process == 0){
                    DataManager.selectedMaterial = All.get(pos);
                    startActivity(new Intent(getApplicationContext(),DetailMaterialActivity.class));
                    finish();
                } else if(DataManager.process == 1){
                    DataManager.selectedMaterial = InProcess.get(pos);
                    startActivity(new Intent(getApplicationContext(),DetailMaterialActivity.class));
                    finish();
                } else if(DataManager.process == 2){
                    DataManager.selectedMaterial = OutProcess.get(pos);
                    startActivity(new Intent(getApplicationContext(),DetailMaterialActivity.class));
                    finish();
                }

            }
        });

        edt_search_mat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String matNameSearch = edt_search_mat.getText().toString();
                allMaterials = new ArrayList<>();
                InProcessMaterials = new ArrayList<>();
                OutProcessMaterials = new ArrayList<>();
                if (DataManager.process == 0){
                    for (int i = 0; i < DataManager.materialsList.size(); i++){
                        if (DataManager.materialsList.get(i).getMaterialName().toLowerCase().contains(matNameSearch.toLowerCase())){
                            allMaterials.add(DataManager.materialsList.get(i));
                        }
                    }
                    All = allMaterials;
                    materialAdapter.setData(All);
                } else if (DataManager.process == 1) {
                    for (int i = 0; i < DataManager.materialsInProcessList.size(); i++){
                        if (DataManager.materialsInProcessList.get(i).getMaterialName().toLowerCase().contains(matNameSearch.toLowerCase())){
                            InProcessMaterials.add(DataManager.materialsInProcessList.get(i));
                        }
                    }
                    InProcess = InProcessMaterials;
                    materialAdapter.setData(InProcess);
                } else if(DataManager.process == 2){
                    for (int i = 0; i < DataManager.materialsInProcessList.size(); i++){
                        if (DataManager.materialsInProcessList.get(i).getMaterialName().toLowerCase().contains(matNameSearch.toLowerCase())){
                            OutProcessMaterials.add(DataManager.materialsInProcessList.get(i));
                        }
                    }
                    OutProcess = OutProcessMaterials;
                    materialAdapter.setData(OutProcess);
                }

            }
        });
    }



    private void setUpView() {
        if (DataManager.materialsInProcessList.size() == 0 && DataManager.materialsOutProcessList.size() == 0){
            for (int i = 0; i < DataManager.materialsList.size(); i++){
                if (DataManager.materialsList.get(i).isMaterialStatus()){
                    DataManager.materialsInProcessList.add(DataManager.materialsList.get(i));
                }
                else DataManager.materialsOutProcessList.add(DataManager.materialsList.get(i));
            }
        }
        if (DataManager.process == 0){
            materialAdapter = new MaterialAdapter(this, DataManager.materialsList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcv_material.setLayoutManager(linearLayoutManager);
            rcv_material.setAdapter(materialAdapter);
            materialAdapter.setData(DataManager.materialsList);
        } else if (DataManager.process == 1) {
            materialAdapter = new MaterialAdapter(this, DataManager.materialsInProcessList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcv_material.setLayoutManager(linearLayoutManager);
            rcv_material.setAdapter(materialAdapter);
            materialAdapter.setData(DataManager.materialsInProcessList);
        } else if (DataManager.process == 2) {
            materialAdapter = new MaterialAdapter(this, DataManager.materialsOutProcessList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcv_material.setLayoutManager(linearLayoutManager);
            rcv_material.setAdapter(materialAdapter);
            materialAdapter.setData(DataManager.materialsOutProcessList);
        }


    }

    private void initView() {
        imgView_Add_Material = findViewById(R.id.imgView_Add_Material);
        rcv_material = findViewById(R.id.rcv_material);
        btn_mat_backToHome = findViewById(R.id.btn_mat_backToHome);
        edt_search_mat = findViewById(R.id.edt_search_mat);
    }
}
