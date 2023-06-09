package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.API.ApiService;
import com.example.Adapter.MaterialTypeAdapter;
import com.example.Adapter.PrimaryUnitsAdapter;
import com.example.DataManager;
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
    private ImageView btn_primary_unit_backToHome, imgView_Add_Primary_Unit;
    private EditText search_Primary_Unit;
    private List<PrimaryUnits> primaryUnitsList;
    private List<PrimaryUnits> All = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_primary_units);


        All=DataManager.primaryUnitsList;
        initView();
        loadData();
        onClick();

    }

    private void initView() {
        rcv_primary_unit = findViewById(R.id.rcv_primary_unit);
        btn_primary_unit_backToHome = findViewById(R.id.btn_primary_unit_backToHome);
        imgView_Add_Primary_Unit = findViewById(R.id.imgView_Add_Primary_Unit);
        search_Primary_Unit = findViewById(R.id.search_Primary_Unit);
    }

    private void onClick() {
        btn_primary_unit_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
        imgView_Add_Primary_Unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddPrimaryUnit.class));
                finish();
            }
        });
        primaryUnitsAdapter.setOnClickListener(new PrimaryUnitsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DataManager.selectedPrimaryUnit = All.get(pos);
                startActivity(new Intent(getApplicationContext(), DetailPrimaryUnitActivity.class));
                finish();
            }
        });
        search_Primary_Unit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                primaryUnitsList = new ArrayList<>();
                String unitNameSearch = search_Primary_Unit.getText().toString();
                for (int i = 0; i < DataManager.primaryUnitsList.size(); i++){
                    if (DataManager.primaryUnitsList.get(i).getPrimaryUnitName().toLowerCase().contains(unitNameSearch.toLowerCase())){
                        primaryUnitsList.add(DataManager.primaryUnitsList.get(i));
                    }
                }
                All = primaryUnitsList;
                primaryUnitsAdapter.setData(All);
            }
        });
    }

    private void loadData() {
        primaryUnitsAdapter = new PrimaryUnitsAdapter(this, DataManager.primaryUnitsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_primary_unit.setLayoutManager(linearLayoutManager);
        rcv_primary_unit.setAdapter(primaryUnitsAdapter);
        primaryUnitsAdapter.setData(DataManager.primaryUnitsList);

    }
}