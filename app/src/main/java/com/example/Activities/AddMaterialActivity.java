package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.API.ApiService;
import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMaterialActivity extends AppCompatActivity {
    private EditText edt_add_material_name, edt_add_material_description, edt_add_material_quantity, edt_add_material_img;
    private Spinner spin_add_material_type, spin_add_material_unit, spin_add_material_product;
    private Button btn_create_material;
    private ImageView btn_add_mat_backToMaterial;

    private String[] materialNames;
    private  List<MaterialTypes> materialTypesList;
    private List<String> materialNameList;
    private String currentMatId;

    private Materials materials;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_material);


        initView();
        loadSpinner();
        onClick();
    }

    private void loadSpinner() {
        materialTypesList = new ArrayList<>();
        materialNameList = new ArrayList<>();
        ApiService.api.GetMaterialTypes().enqueue(new Callback<List<MaterialTypes>>() {
            @Override
            public void onResponse(Call<List<MaterialTypes>> call, Response<List<MaterialTypes>> response) {

                if (response.body() != null){

                    materialTypesList.addAll(response.body());

                    for (int i = 0; i <  materialTypesList.size(); i++ ){
                        materialNameList.add(materialTypesList.get(i).getTypeName());
                    }

                    if (materialNameList.size() >0){
                        materialNames=new String[materialNameList.size()];
                        for(int i = 0 ; i<materialNameList.size();i++){
                            materialNames[i] = materialNameList.get(i);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,materialNames);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin_add_material_type.setAdapter(adapter);
                    }
                    else Toast.makeText(getApplicationContext(), "materialTypesList.size() = 0", Toast.LENGTH_SHORT).show();



                }
            }
            @Override
            public void onFailure(Call<List<MaterialTypes>> call, Throwable t) {
                Log.e("TAG", "err: "+t.toString() );
            }
        });
        materials=new Materials();

        spin_add_material_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idType = materialTypesList.get(position).getIdType();
                materials.setIdType(idType);
                Log.e("TAG", "onResponse: "+materials.getIdType() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void onClick() {
        btn_add_mat_backToMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                finish();
            }
        });

        btn_create_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                finish();
            }
        });
    }


    private void initView() {
        edt_add_material_name = findViewById(R.id.edt_add_material_name);
        edt_add_material_description = findViewById(R.id.edt_add_material_description);
        edt_add_material_quantity = findViewById(R.id.edt_add_material_quantity);
        spin_add_material_type = findViewById(R.id.spin_add_material_type);
        spin_add_material_unit = findViewById(R.id.spin_add_material_unit);
        spin_add_material_product = findViewById(R.id.spin_add_material_product);
        edt_add_material_img = findViewById(R.id.edt_add_material_img);
        btn_add_mat_backToMaterial = findViewById(R.id.btn_add_mat_backToMaterial);
        btn_create_material = findViewById(R.id.btn_create_material);
    }
}
