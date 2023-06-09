package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMaterialActivity extends AppCompatActivity {
    private EditText edt_edit_material_name, edt_edit_material_description, edt_edit_material_quantity, edt_edit_material_img;
    private Spinner spin_edit_material_type, spin_edit_material_unit, spin_edit_material_product;
    private Button btn_edit_material;
    private ImageView imaview_edit_mat_backToDetail;

    // Spinner Material Type
    private String[] materialNames;
    private Materials materials;
    // Spinner Primary Unit
    private String[] primaryUnitName;

    // Spinner Product
    private String[] productName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_material);


        initView();
        loadData();
        onClick();
    }

    private void onClick() {
        imaview_edit_mat_backToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetailMaterialActivity.class));
                finish();
            }
        });

        btn_edit_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materials.setIdMaterial(DataManager.selectedMaterial.getIdMaterial());
                materials.setMaterialName(edt_edit_material_name.getText().toString());
                materials.setMaterialDescription(edt_edit_material_description.getText().toString());
                materials.setPrimaryQuantity(Integer.valueOf(edt_edit_material_quantity.getText().toString()));
                materials.setMaterialStatus(DataManager.selectedMaterial.isMaterialStatus());
                if (edt_edit_material_img.getText().toString().length() == 0) {
                    materials.setMaterialIMG("");
                } else materials.setMaterialIMG(edt_edit_material_img.getText().toString());

                ApiService.api.PutMaterials(materials.getIdMaterial(), materials).enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            for (int i = 0; i < DataManager.materialsList.size(); i++) {
                                if (DataManager.materialsList.get(i).getIdMaterial().equals(materials.getIdMaterial())) {
                                    DataManager.selectedMaterial=materials;
                                    DataManager.materialsList.set(i, materials);
                                }
                            }
                            startActivity(new Intent(getApplicationContext(), DetailMaterialActivity.class));
                            Toast.makeText(EditMaterialActivity.this, "Sửa vật liệu thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditMaterialActivity.this, "Thêm vật liệu thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadData() {
        materials = new Materials();
        // ---------------Spinner Material Type------------------------
        edt_edit_material_name.setText(DataManager.selectedMaterial.getMaterialName());
        edt_edit_material_description.setText(DataManager.selectedMaterial.getMaterialDescription());
        edt_edit_material_quantity.setText(String.valueOf(DataManager.selectedMaterial.getPrimaryQuantity()));
        edt_edit_material_img.setText(DataManager.selectedMaterial.getMaterialIMG());

        if (DataManager.materialTypesList.size() > 0) {
            materialNames = new String[DataManager.materialTypesList.size()];
            for (int i = 0; i < DataManager.materialTypesList.size(); i++) {
                materialNames[i] = DataManager.materialTypesList.get(i).getTypeName();
            }
            ArrayAdapter<String> adapterMatType = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, materialNames);
            adapterMatType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_edit_material_type.setAdapter(adapterMatType);
        }
        spin_edit_material_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idType = DataManager.materialTypesList.get(position).getIdType();
                materials.setIdType(idType);
                Log.e("TAG", "onResponse: " + materials.getIdType());
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
            spin_edit_material_unit.setAdapter(adapterUnit);
        }
        spin_edit_material_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            spin_edit_material_product.setAdapter(adapterProduct);
        }
        spin_edit_material_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void initView() {
        edt_edit_material_name = findViewById(R.id.edt_edit_material_name);
        edt_edit_material_description = findViewById(R.id.edt_edit_material_description);
        edt_edit_material_quantity = findViewById(R.id.edt_edit_material_quantity);
        edt_edit_material_img = findViewById(R.id.edt_edit_material_img);
        spin_edit_material_type = findViewById(R.id.spin_edit_material_type);
        spin_edit_material_unit = findViewById(R.id.spin_edit_material_unit);
        spin_edit_material_product = findViewById(R.id.spin_edit_material_product);
        btn_edit_material = findViewById(R.id.btn_edit_material);
        imaview_edit_mat_backToDetail = findViewById(R.id.imaview_edit_mat_backToDetail);
    }
}
