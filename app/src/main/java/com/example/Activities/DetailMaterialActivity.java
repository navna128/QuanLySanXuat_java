package com.example.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMaterialActivity extends AppCompatActivity {
    private TextView txtView_detail_mat_id, txtView_detail_mat_name, txtView_detail_mat_description, txtView_detail_mat_quantity, txtView_detail_mat_status, txtView_detail_mat_unit, txtView_detail_mat_product, txtView_detail_mat_type;
    private Button btn_detail_delete_mat, btn_detail_go_to_edit_mat;
    private ImageView imgView_detail_mat_image, imgView_detail_back_to_mat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.detail_material);

        InitView();
        setData();
        onClick();
    }

    private void onClick() {
        imgView_detail_back_to_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MaterialActivity.class));
                finish();
            }
        });
        btn_detail_delete_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailMaterialActivity.this)
                        .setTitle("Delete Material")
                        .setMessage("Do you want to delete material: " + DataManager.selectedMaterial.getMaterialName() + "?")
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService.api.DeleteMaterial(DataManager.selectedMaterial.getIdMaterial()).enqueue(new Callback<Materials>() {
                                    @Override
                                    public void onResponse(Call<Materials> call, Response<Materials> response) {
                                        if (response.body()!=null){
                                            DataManager.materialsList.remove(DataManager.selectedMaterial);
                                            startActivity(new Intent(getApplicationContext(),MaterialActivity.class));
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Materials> call, Throwable t) {
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
        btn_detail_go_to_edit_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EditMaterialActivity.class));
                finish();
            }
        });
    }

    private void setData() {
        txtView_detail_mat_id.setText(DataManager.selectedMaterial.getIdMaterial());
        txtView_detail_mat_name.setText(DataManager.selectedMaterial.getMaterialName());
        txtView_detail_mat_description.setText(DataManager.selectedMaterial.getMaterialDescription());
        txtView_detail_mat_quantity.setText(String.valueOf(DataManager.selectedMaterial.getPrimaryQuantity()));
        if (DataManager.selectedMaterial.isMaterialStatus()) {
            txtView_detail_mat_status.setText("In process");
        } else txtView_detail_mat_status.setText("Out process");


        if (DataManager.materialTypesList != null) {
            for (MaterialTypes materialTypes : DataManager.materialTypesList) {
                Log.e("mat", "setData: " + DataManager.selectedMaterial.getIdType() );
                Log.e("mat", "setData: " + materialTypes.getIdType() );
                if (materialTypes.getIdType().equals(DataManager.selectedMaterial.getIdType())) {
                    txtView_detail_mat_type.setText(materialTypes.getTypeName());

                }
            }
        }

        if (DataManager.primaryUnitsList != null) {
            for (PrimaryUnits primaryunit : DataManager.primaryUnitsList) {
                if (primaryunit.getIdPrimaryUnit().equals(DataManager.selectedMaterial.getIdPrimaryUnit())) {
                    txtView_detail_mat_unit.setText(primaryunit.getPrimaryUnitName());
                }
            }
        }

        if (DataManager.productsList != null) {
            for (Products products : DataManager.productsList) {
                if (products.getIdProduct().equals(DataManager.selectedMaterial.getIdProduct())) {

                    txtView_detail_mat_product.setText(products.getProductName());
                }
            }
        }
        if (DataManager.selectedMaterial.getMaterialIMG().length() >0) {
            Glide.with(this).load(DataManager.selectedMaterial.getMaterialIMG()).override(400, 400).into(imgView_detail_mat_image);
        }
    }

    private void InitView() {
        txtView_detail_mat_id = findViewById(R.id.txtView_detail_mat_id);
        txtView_detail_mat_name = findViewById(R.id.txtView_detail_mat_name);
        txtView_detail_mat_description = findViewById(R.id.txtView_detail_mat_description);
        txtView_detail_mat_quantity = findViewById(R.id.txtView_detail_mat_quantity);
        txtView_detail_mat_status = findViewById(R.id.txtView_detail_mat_status);
        txtView_detail_mat_unit = findViewById(R.id.txtView_detail_mat_unit);
        txtView_detail_mat_product = findViewById(R.id.txtView_detail_mat_product);
        txtView_detail_mat_type = findViewById(R.id.txtView_detail_mat_type);
        btn_detail_delete_mat = findViewById(R.id.btn_detail_delete_mat);
        btn_detail_go_to_edit_mat = findViewById(R.id.btn_detail_go_to_edit_mat);
        imgView_detail_mat_image = findViewById(R.id.imgView_detail_mat_image);
        imgView_detail_back_to_mat = findViewById(R.id.imgView_detail_back_to_mat);
    }
}
