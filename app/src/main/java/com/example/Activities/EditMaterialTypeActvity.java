package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.MaterialTypes;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMaterialTypeActvity extends AppCompatActivity {

    private ImageView imgView_edit_mattype_backToDetail;
    private Button btn_edit_material_type;
    private EditText edt_edit_material_type_name, edt_edit_material_type_description;
    private MaterialTypes materialTypes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_material_type);


        initView();
        loadData();
        onClick();
    }

    private void onClick() {



        imgView_edit_mattype_backToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetailMaterialTypeActivity.class));
                finish();
            }
        });

        btn_edit_material_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialTypes = new MaterialTypes();
                materialTypes.setIdType(DataManager.selectedMaterialType.getIdType());
                materialTypes.setTypeName(edt_edit_material_type_name.getText().toString());
                materialTypes.setTypelDescription(edt_edit_material_type_description.getText().toString());

                ApiService.api.PutMaterialType(materialTypes.getIdType(),materialTypes).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            for (int i =0 ; i < DataManager.materialTypesList.size(); i++){
                                if (DataManager.materialTypesList.get(i).getIdType().equals(materialTypes.getIdType())){
                                    DataManager.materialTypesList.set(i,materialTypes);
                                }
                            }
                            startActivity(new Intent(getApplicationContext(),DetailMaterialTypeActivity.class));
                            finish();
                            Toast.makeText(EditMaterialTypeActvity.this, "Sửa loại sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(EditMaterialTypeActvity.this, "Sửa loại sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditMaterialTypeActvity.this, "Sửa loại sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadData() {
        edt_edit_material_type_name.setText(DataManager.selectedMaterialType.getTypeName());
        edt_edit_material_type_description.setText(DataManager.selectedMaterialType.getTypelDescription());
    }

    private void initView() {
        edt_edit_material_type_name = findViewById(R.id.edt_edit_material_type_name);
        edt_edit_material_type_description = findViewById(R.id.edt_edit_material_type_description);
        btn_edit_material_type = findViewById(R.id.btn_edit_material_type);
        imgView_edit_mattype_backToDetail = findViewById(R.id.imgView_edit_mattype_backToDetail);
    }
}
