package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
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
import com.example.Models.PrimaryUnits;
import com.example.quanlysanxuat.R;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPrimaryUnit extends AppCompatActivity {

    private ImageView btn_add_unit_backToMaterialType;
    private Button btn_create_primary_unit;
    private EditText edt_add_primary_unit_name, edt_add_primary_unit_description;
    private PrimaryUnits primaryUnits;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
                primaryUnits = new PrimaryUnits();
                UUID uuid = UUID.randomUUID();
                String id= uuid.toString().substring(0,10);
                Log.e("TAG", "id: "+id );
                primaryUnits.setIdPrimaryUnit(id);
                primaryUnits.setPrimaryUnitName(edt_add_primary_unit_name.getText().toString());
                primaryUnits.setPrimaryUnitlDescription(edt_add_primary_unit_description.getText().toString());

                ApiService.api.PostPrimaryUnit(primaryUnits).enqueue(new Callback<PrimaryUnits>() {
                    @Override
                    public void onResponse(Call<PrimaryUnits> call, Response<PrimaryUnits> response) {
                        if (response.body()!=null) {
                            DataManager.primaryUnitsList.add(primaryUnits);
                            startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PrimaryUnits> call, Throwable t) {
                    }
                });

            }
        });
    }

    private void initView() {
        btn_add_unit_backToMaterialType = findViewById(R.id.btn_add_unit_backToMaterialType);
        btn_create_primary_unit = findViewById(R.id.btn_create_primary_unit);
        edt_add_primary_unit_name = findViewById(R.id.edt_add_primary_unit_name);
        edt_add_primary_unit_description = findViewById(R.id.edt_add_primary_unit_description);
    }
}
