package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPrimaryUnitActivity extends AppCompatActivity {
    private ImageView imgView_edit_unit_backToMaterialType;
    private Button btn_edit_primary_unit;
    private EditText edt_edit_primary_unit_name, edt_edit_primary_unit_description;
    private PrimaryUnits primaryUnits;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_pimaryunit);
        initView();
        loadData();
        onClick();
    }

    private void onClick() {
        imgView_edit_unit_backToMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DetailPrimaryUnitActivity.class));
                finish();
            }
        });

        btn_edit_primary_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryUnits = new PrimaryUnits();
                primaryUnits.setIdPrimaryUnit(DataManager.selectedPrimaryUnit.getIdPrimaryUnit());
                primaryUnits.setPrimaryUnitName(edt_edit_primary_unit_name.getText().toString());
                primaryUnits.setPrimaryUnitlDescription(edt_edit_primary_unit_description.getText().toString());

                ApiService.api.PutPrimaryUnit(primaryUnits.getIdPrimaryUnit(),primaryUnits).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            DataManager.selectedPrimaryUnit = primaryUnits;
                            for (int i = 0 ; i < DataManager.materialsList.size() ; i++){
                                if (DataManager.primaryUnitsList.get(i).getIdPrimaryUnit().equals(primaryUnits.getIdPrimaryUnit())){
                                    DataManager.primaryUnitsList.set(i,primaryUnits);
                                }
                            }
                            Toast.makeText(EditPrimaryUnitActivity.this, "Sửa đơn vị thành công ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),DetailPrimaryUnitActivity.class));
                            finish();
                        }
                        else Toast.makeText(EditPrimaryUnitActivity.this, "Sửa đơn vị không thành công ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditPrimaryUnitActivity.this, "Sửa đơn vị thành công ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadData() {
        edt_edit_primary_unit_name.setText(DataManager.selectedPrimaryUnit.getPrimaryUnitName());
        edt_edit_primary_unit_description.setText(DataManager.selectedPrimaryUnit.getPrimaryUnitlDescription());
    }

    private void initView() {
        edt_edit_primary_unit_name=findViewById(R.id.edt_edit_primary_unit_name);
        edt_edit_primary_unit_description=findViewById(R.id.edt_edit_primary_unit_description);
        imgView_edit_unit_backToMaterialType=findViewById(R.id.imgView_edit_unit_backToMaterialType);
        btn_edit_primary_unit=findViewById(R.id.btn_edit_primary_unit);
    }
}
