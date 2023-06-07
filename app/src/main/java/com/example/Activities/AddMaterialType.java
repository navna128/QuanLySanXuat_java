package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysanxuat.R;

public class AddMaterialType extends AppCompatActivity {

    private ImageView btn_add_mattype_backToMaterialType;
    private Button btn_create_material_type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_material_type);

        initView();
        onClick();

    }

    private void onClick() {
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
                startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        btn_add_mattype_backToMaterialType = findViewById(R.id.btn_add_mattype_backToMaterialType);
        btn_create_material_type = findViewById(R.id.btn_create_material_type);
    }
}
