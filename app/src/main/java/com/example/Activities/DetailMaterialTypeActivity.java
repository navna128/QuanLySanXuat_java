package com.example.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.Models.Materials;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMaterialTypeActivity extends AppCompatActivity {
    private TextView txtView_detail_mat_type_id, txtView_detail_mat_type_name, txtView_detail_mat_type_description;
    private ImageView imgView_detail_back_to_mat_type;
    private Button btn_detail_delete_mat_type, btn_detail_go_to_edit_mat_type;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.detail_material_type);
        
        InitView();
        setData();
        onClick();
    }

    private void onClick() {
        imgView_detail_back_to_mat_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                finish();
            }
        });
        btn_detail_delete_mat_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailMaterialTypeActivity.this)
                        .setTitle("Delete Material Type")
                        .setMessage("Do you want to delete material type: " + DataManager.selectedMaterialType.getTypeName() + "?")
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService.api.DeleteMaterialType(DataManager.selectedMaterialType.getIdType()).enqueue(new Callback<MaterialTypes>() {
                                    @Override
                                    public void onResponse(Call<MaterialTypes> call, Response<MaterialTypes> response) {
                                        if(response.isSuccessful()){
                                            DataManager.materialTypesList.remove(DataManager.selectedMaterialType);
                                            startActivity(new Intent(getApplicationContext(),MaterialTypeActivity.class));
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MaterialTypes> call, Throwable t) {
                                    }
                                });
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
        btn_detail_go_to_edit_mat_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditMaterialTypeActvity.class));
                finish();
            }
        });
    }

    private void setData() {
        txtView_detail_mat_type_id.setText(DataManager.selectedMaterialType.getIdType());
        txtView_detail_mat_type_name.setText(DataManager.selectedMaterialType.getTypeName());
        txtView_detail_mat_type_description.setText(DataManager.selectedMaterialType.getTypelDescription());
    }

    private void InitView() {
        txtView_detail_mat_type_id = findViewById(R.id.txtView_detail_mat_type_id);
        txtView_detail_mat_type_name = findViewById(R.id.txtView_detail_mat_type_name);
        txtView_detail_mat_type_description = findViewById(R.id.txtView_detail_mat_type_description);
        imgView_detail_back_to_mat_type = findViewById(R.id.imgView_detail_back_to_mat_type);
        btn_detail_delete_mat_type = findViewById(R.id.btn_detail_delete_mat_type);
        btn_detail_go_to_edit_mat_type = findViewById(R.id.btn_detail_go_to_edit_mat_type);
    }
}
