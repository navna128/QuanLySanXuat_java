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
import com.example.DataManager;
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

    private Materials materials;
    // Spinner Material Type
    private String[] materialNames;


    // Spinner Primary Unit
    private String[] primaryUnitName;
    private PrimaryUnits primaryUnits;

    // Spinner Product
    private String[] productName;
    private Products products;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_material);


        initView();
        loadData();
        onClick();
    }

    private void loadData() {
        materials = new Materials();
        // ---------------Spinner Material Type------------------------
        if (DataManager.materialTypesList.size() > 0) {
            materialNames = new String[DataManager.materialTypesList.size()];
            for (int i = 0; i < DataManager.materialTypesList.size(); i++) {
                materialNames[i] = DataManager.materialTypesList.get(i).getTypeName();
            }
            ArrayAdapter<String> adapterMatType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, materialNames);
            adapterMatType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_add_material_type.setAdapter(adapterMatType);
        } else
            Toast.makeText(getApplicationContext(), "materialTypesList.size() = 0", Toast.LENGTH_SHORT).show();

        spin_add_material_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idType = DataManager.materialTypesList.get(position).getIdType();
                materials.setIdType(idType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // ---------------Spinner Primary Unit------------------------


        if (DataManager.primaryUnitsList.size() > 0) {
            primaryUnitName = new String[DataManager.primaryUnitsList.size()];
            for (int i = 0; i < DataManager.primaryUnitsList.size(); i++) {
                primaryUnitName[i] = DataManager.primaryUnitsList.get(i).getPrimaryUnitName();
            }
            ArrayAdapter<String> adapterUnit = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, primaryUnitName);
            adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_add_material_unit.setAdapter(adapterUnit);
        }


        spin_add_material_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idUnit = DataManager.primaryUnitsList.get(position).getIdPrimaryUnit();
                materials.setIdPrimaryUnit(idUnit);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ---------------Spinner Product------------------------


        if (DataManager.productsList.size() > 0) {
            productName = new String[DataManager.productsList.size()];
            for (int i = 0; i < DataManager.productsList.size(); i++) {
                productName[i] = DataManager.productsList.get(i).getProductName();
            }
            ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productName);
            adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_add_material_product.setAdapter(adapterProduct);
        }

        spin_add_material_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idProduct = DataManager.productsList.get(position).getIdProduct();
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
                startActivity(new Intent(getApplicationContext(), MaterialActivity.class));
                finish();
            }
        });

        btn_create_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID uuid = UUID.randomUUID();
                String id = uuid.toString().substring(0, 10);
                materials.setIdMaterial(id);
                materials.setMaterialName(edt_add_material_name.getText().toString());
                if (edt_add_material_description.getText().toString() != null) {
                    materials.setMaterialDescription(edt_add_material_description.getText().toString());
                } else materials.setMaterialDescription("");

                int quantity = Integer.valueOf(edt_add_material_quantity.getText().toString());
                materials.setPrimaryQuantity(quantity);
                if (edt_add_material_img.getText().toString() != null) {
                    materials.setMaterialIMG(edt_add_material_img.getText().toString());
                } else materials.setMaterialIMG("");
                Log.e("TAG1", "material: " + materials.getIdMaterial() + " " + materials.getMaterialName() + " "
                        + materials.getMaterialDescription() + " " + materials.getPrimaryQuantity() + " " + materials.getIdType()
                        + " " + materials.getIdPrimaryUnit() + " " + materials.getIdProduct())
                ;
                ApiService.api.PostMaterial(materials).enqueue(new Callback<Materials>() {
                    @Override
                    public void onResponse(Call<Materials> call, Response<Materials> response) {

                        if (response.body() != null) {
                            DataManager.materialsList.add(materials);
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
