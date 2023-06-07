package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysanxuat.R;

public class AddPrimaryUnit extends AppCompatActivity {

    private ImageView btn_add_unit_backToMaterialType;
    private Button btn_create_primary_unit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_primary_unit);

        initView();
        onClick();

    }

    private void onClick() {
        btn_add_unit_backToMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                finish();
            }
        });
        btn_create_primary_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        btn_add_unit_backToMaterialType = findViewById(R.id.btn_add_unit_backToMaterialType);
        btn_create_primary_unit = findViewById(R.id.btn_create_primary_unit);
    }
}
