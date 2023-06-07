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
import com.example.Models.MaterialTypes;
import com.example.quanlysanxuat.R;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMaterialType extends AppCompatActivity {

    private ImageView btn_add_mattype_backToMaterialType;
    private Button btn_create_material_type;
    private EditText edt_add_material_type_name, edt_add_material_type_description;
    private MaterialTypes materialTypes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_material_type);

        initView();
        onClick();

    }

    private void onClick() {
        materialTypes = new MaterialTypes();
        btn_add_mattype_backToMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                finish();
            }
        });
        btn_create_material_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID uuid = UUID.randomUUID();
                String id= uuid.toString().substring(0,10);
                materialTypes.setIdType(id);
                materialTypes.setTypeName(edt_add_material_type_name.getText().toString());
                materialTypes.setTypelDescription(edt_add_material_type_description.getText().toString());

                ApiService.api.PostMaterialType(materialTypes).enqueue(new Callback<MaterialTypes>() {
                    @Override
                    public void onResponse(Call<MaterialTypes> call, Response<MaterialTypes> response) {
                        if (response.body()!=null) {
                            startActivity(new Intent(AddMaterialType.this, MaterialTypeActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MaterialTypes> call, Throwable t) {

                    }
                });
                startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        btn_add_mattype_backToMaterialType = findViewById(R.id.btn_add_mattype_backToMaterialType);
        btn_create_material_type = findViewById(R.id.btn_create_material_type);
        edt_add_material_type_name = findViewById(R.id.edt_add_material_type_name);
        edt_add_material_type_description=findViewById(R.id.edt_add_material_type_description);
    }
}
