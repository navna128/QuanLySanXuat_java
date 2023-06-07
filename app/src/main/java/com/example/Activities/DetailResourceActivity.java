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
import com.example.Models.Products;
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailResourceActivity extends AppCompatActivity {
    private TextView txtView_detail_resource_id, txtView_detail_resource_name, txtView_detail_resource_description;
    private ImageView imgView_detail_back_to_resource;
    private Button btn_detail_delete_resource, btn_detail_go_to_edit_resource;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.detail_resource);

        InitView();
        setData();
        onClick();
    }

    private void onClick() {
        imgView_detail_back_to_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ResourceActivity.class));
                finish();
            }
        });

        btn_detail_delete_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailResourceActivity.this)
                        .setTitle("Delete Resource")
                        .setMessage("Do you want to delete resource: " + DataManager.selectedResource.getResourceName() + "?")
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService.api.DeleteResource(String.valueOf(DataManager.selectedResource.getIdResource())).enqueue(new Callback<Resources>() {
                                    @Override
                                    public void onResponse(Call<Resources> call, Response<Resources> response) {
                                    }

                                    @Override
                                    public void onFailure(Call<Resources> call, Throwable t) {
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(),ResourceActivity.class));
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
        txtView_detail_resource_id.setText(String.valueOf(DataManager.selectedResource.getIdResource()));
        txtView_detail_resource_name.setText(DataManager.selectedResource.getResourceName());
        txtView_detail_resource_description.setText(DataManager.selectedResource.getResourcelDescription());
    }

    private void InitView() {
        txtView_detail_resource_id = findViewById(R.id.txtView_detail_resource_id);
        txtView_detail_resource_name = findViewById(R.id.txtView_detail_resource_name);
        txtView_detail_resource_description = findViewById(R.id.txtView_detail_resource_description);
        imgView_detail_back_to_resource = findViewById(R.id.imgView_detail_back_to_resource);
        btn_detail_delete_resource = findViewById(R.id.btn_detail_delete_resource);
        btn_detail_go_to_edit_resource = findViewById(R.id.btn_detail_go_to_edit_resource);
    }
}
