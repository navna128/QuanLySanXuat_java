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

import com.example.API.ApiService;
import com.example.Adapter.MaterialTypeAdapter;
import com.example.Adapter.ResourceAdapter;
import com.example.DataManager;
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
    private ImageView btn_resource_backToHome, imgView_Add_Resource;
    private EditText search_Resource;
    private List<Resources> resourcesList;
    private List<Resources> All;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_resource);

        All = DataManager.resourcesList;
        initView();
        loadData();
        onClick();
    }

    private void onClick() {
        btn_resource_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        imgView_Add_Resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddResource.class));
                finish();
            }
        });

        resourceAdapter.setOnClickListener(new ResourceAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DataManager.selectedResource = All.get(pos);
                startActivity(new Intent(getApplicationContext(), DetailResourceActivity.class));
                finish();
            }
        });
        search_Resource.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                resourcesList =new ArrayList<>();
                String resourceNameSearch = search_Resource.getText().toString();
                for (int i = 0; i < DataManager.resourcesList.size(); i++){
                    if (DataManager.resourcesList.get(i).getResourceName().toLowerCase().contains(resourceNameSearch.toLowerCase())){
                        resourcesList.add(DataManager.resourcesList.get(i));
                    }
                }
                All = resourcesList;
                resourceAdapter.setData(All);
            }
        });
    }

    private void loadData() {
        resourceAdapter = new ResourceAdapter(this, DataManager.resourcesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_resource.setLayoutManager(linearLayoutManager);
        rcv_resource.setAdapter(resourceAdapter);
        resourceAdapter.setData(DataManager.resourcesList);
    }

    private void initView() {
        rcv_resource = findViewById(R.id.rcv_resource);
        btn_resource_backToHome = findViewById(R.id.btn_resource_backToHome);
        imgView_Add_Resource = findViewById(R.id.imgView_Add_Resource);
        search_Resource = findViewById(R.id.search_Resource);
    }
}