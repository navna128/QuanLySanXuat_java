package com.example.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.MaterialTypes;
import com.example.Models.PrimaryUnits;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPrimaryUnitActivity extends AppCompatActivity {
    private TextView txtView_detail_unit_id, txtView_detail_unit_name, txtView_detail_unit_description;
    private ImageView imgView_detail_back_to_unit;
    private Button btn_detail_delete_unit, btn_detail_go_to_edit_unit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.detail_primary_unit);

        InitView();
        setData();
        onClick();

    }

    private void onClick() {
        imgView_detail_back_to_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                finish();
            }
        });
        btn_detail_delete_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailPrimaryUnitActivity.this)
                        .setTitle("Delete Primary Unit")
                        .setMessage("Do you want to delete primary unit: " + DataManager.selectedPrimaryUnit.getPrimaryUnitName() + "?")
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService.api.DeletePrimaryUnit(DataManager.selectedPrimaryUnit.getIdPrimaryUnit()).enqueue(new Callback<PrimaryUnits>() {
                                    @Override
                                    public void onResponse(Call<PrimaryUnits> call, Response<PrimaryUnits> response) {
                                    }

                                    @Override
                                    public void onFailure(Call<PrimaryUnits> call, Throwable t) {
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(),PrimaryUnitsActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    private void setData() {
        txtView_detail_unit_id.setText(DataManager.selectedPrimaryUnit.getIdPrimaryUnit());
        txtView_detail_unit_name.setText(DataManager.selectedPrimaryUnit.getPrimaryUnitName());
        txtView_detail_unit_description.setText(DataManager.selectedPrimaryUnit.getPrimaryUnitlDescription());
    }

    private void InitView() {
        txtView_detail_unit_id = findViewById(R.id.txtView_detail_unit_id);
        txtView_detail_unit_name = findViewById(R.id.txtView_detail_unit_name);
        txtView_detail_unit_description = findViewById(R.id.txtView_detail_unit_description);
        imgView_detail_back_to_unit = findViewById(R.id.imgView_detail_back_to_unit);
        btn_detail_delete_unit = findViewById(R.id.btn_detail_delete_unit);
        btn_detail_go_to_edit_unit = findViewById(R.id.btn_detail_go_to_edit_unit);
    }
}
