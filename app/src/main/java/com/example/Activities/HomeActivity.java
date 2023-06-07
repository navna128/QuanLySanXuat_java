package com.example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.quanlysanxuat.R;

public class HomeActivity extends AppCompatActivity {
    private TextView txtUserName;
    private CardView CardViewMaterial;
    private CardView CardViewPrimaryUnit;
    private CardView CardViewMaterialType;
    private CardView CardViewProduct;
    private CardView CardViewResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        initView();
        onClick();

    }

    private void onClick() {
        CardViewMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                finish();
            }
        });
        CardViewMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                finish();
            }
        });
        CardViewPrimaryUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                finish();
            }
        });
        CardViewResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResourceActivity.class));
                finish();
            }
        });
        CardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        txtUserName = findViewById(R.id.txtUserName);
        CardViewMaterial = findViewById(R.id.CardViewMaterial);
        CardViewMaterialType = findViewById(R.id.CardViewMaterialType);
        CardViewPrimaryUnit = findViewById(R.id.CardViewPrimaryUnit);
        CardViewProduct = findViewById(R.id.CardViewProduct);
        CardViewResource = findViewById(R.id.CardViewResource);
    }
}