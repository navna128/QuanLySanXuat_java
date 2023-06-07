package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMaterialActivity extends AppCompatActivity {
    private EditText edt_add_material_name, edt_add_material_description, edt_add_material_quantity, edt_add_material_img;
    private Spinner spin_add_material_type, spin_add_material_unit, spin_add_material_product;
    private Button btn_create_material;
    private ImageView btn_add_mat_backToMaterial;

    // Spinner Material Type
    private String[] materialNames;
    private  List<MaterialTypes> materialTypesList;
    private List<String> materialNameList;
    private Materials materials;

    // Spinner Primary Unit
    private String[] primaryUnitName;
    private List<PrimaryUnits> primaryUnitsList;
    private List<String> primaryUnitNameList;
    private PrimaryUnits primaryUnits;

    // Spinner Product
    private String[] productName;
    private List<Products> productsList;
    private List<String> productNameList;
    private Products products;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_material);


        initView();
        loadData();
        onClick();
    }

    private void loadData() {
        // ---------------Spinner Material Type------------------------
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
                        ArrayAdapter<String> adapterMatType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,materialNames);
                        adapterMatType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin_add_material_type.setAdapter(adapterMatType);
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

        // ---------------Spinner Primary Unit------------------------
        primaryUnitsList = new ArrayList<>();
        primaryUnitNameList = new ArrayList<>();

        ApiService.api.GetPrimaryUnits().enqueue(new Callback<List<PrimaryUnits>>() {
            @Override
            public void onResponse(Call<List<PrimaryUnits>> call, Response<List<PrimaryUnits>> response) {
                if (response.body() != null){
                    primaryUnitsList.addAll(response.body());

                    for (int i =0 ; i < primaryUnitsList.size() ; i++){
                        primaryUnitNameList.add(primaryUnitsList.get(i).getPrimaryUnitName());
                    }

                    if (primaryUnitNameList.size() > 0){
                        primaryUnitName = new String[primaryUnitNameList.size()];
                        for (int i =0 ; i < primaryUnitNameList.size() ; i++){
                            primaryUnitName[i] = primaryUnitNameList.get(i);
                        }
                        ArrayAdapter<String> adapterUnit = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,primaryUnitName);
                        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin_add_material_unit.setAdapter(adapterUnit);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PrimaryUnits>> call, Throwable t) {
            }
        });

        spin_add_material_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idUnit = primaryUnitsList.get(position).getIdPrimaryUnit();
                materials.setIdPrimaryUnit(idUnit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ---------------Spinner Product------------------------
        productsList = new ArrayList<>();
        productNameList = new ArrayList<>();

        ApiService.api.GetProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.body() != null){
                    productsList.addAll(response.body());

                    for (int i =0 ; i < productsList.size() ; i++){
                        productNameList.add(productsList.get(i).getProductName());
                    }

                    if (productNameList.size() > 0){
                        productName = new String[productNameList.size()];
                        for (int i =0 ; i < productNameList.size() ; i++){
                            productName[i] = productNameList.get(i);
                        }
                        ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,productName);
                        adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spin_add_material_product.setAdapter(adapterProduct);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
        spin_add_material_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idProduct = productsList.get(position).getIdProduct();
                materials.setIdProduct(idProduct);
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
                UUID uuid = UUID.randomUUID();
                String id= uuid.toString().substring(0,10);
                materials.setIdMaterial(id);
                materials.setMaterialName(edt_add_material_name.getText().toString());
                if (edt_add_material_description.getText().toString() != null){
                    materials.setMaterialDescription(edt_add_material_description.getText().toString());
                }
                else materials.setMaterialDescription("");

                int quantity = Integer.valueOf(edt_add_material_quantity.getText().toString());
                materials.setPrimaryQuantity(quantity);
                if (edt_add_material_img.getText().toString() != null){
                    materials.setMaterialIMG(edt_add_material_img.getText().toString());
                }
                else materials.setMaterialIMG("");
                Log.e("TAG1", "material: "+materials.getIdMaterial()+" "+ materials.getMaterialName() + " "
                + materials.getMaterialDescription()+" "+materials.getPrimaryQuantity() +" "+materials.getIdType()
                        + " " + materials.getIdPrimaryUnit() +" "+ materials.getIdProduct())
                ;
                ApiService.api.PostMaterial(materials).enqueue(new Callback<Materials>() {
                    @Override
                    public void onResponse(Call<Materials> call, Response<Materials> response) {

                        if (response.body()!=null) {

                            startActivity(new Intent(AddMaterialActivity.this, MaterialActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Materials> call, Throwable t) {
                    }
                });
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
