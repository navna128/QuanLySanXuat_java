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
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditResourceActivity extends AppCompatActivity {
    private ImageView imgView_edit_resource_backToDetailResource;
    private Button btn_edit_resource;
    private EditText edt_edit_resource_name, edt_edit_resource_description;
    private Resources resources;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_resource);
        initView();
        loadData();
        onClick();
    }

    private void onClick() {
        imgView_edit_resource_backToDetailResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DetailResourceActivity.class));
                finish();
            }
        });

        btn_edit_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resources = new Resources();
                resources.setIdResource(DataManager.selectedResource.getIdResource());
                resources.setResourceName(edt_edit_resource_name.getText().toString());
                resources.setResourcelDescription(edt_edit_resource_description.getText().toString());

                Log.e("TAG", "onResponse: "+resources.getResourceName());

                ApiService.api.PutResource(resources.getIdResource(),resources).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Log.e("TAG", "onResponse: "+response.isSuccessful() );
                        if (response.isSuccessful()){
                            DataManager.selectedResource = resources;
                            for (int i = 0; i < DataManager.resourcesList.size(); i++){
                                if (DataManager.resourcesList.get(i).getIdResource() == resources.getIdResource()){
                                    DataManager.resourcesList.set(i,resources);
                                }
                            }
                            Toast.makeText(EditResourceActivity.this, "Sửa thiết bị thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DetailResourceActivity.class));
                            finish();
                        }
                        else Toast.makeText(EditResourceActivity.this, "Sửa thiết bị không thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditResourceActivity.this, "Sửa thiết bị không thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loadData() {
        edt_edit_resource_name.setText(DataManager.selectedResource.getResourceName());
        edt_edit_resource_description.setText(DataManager.selectedResource.getResourcelDescription());
    }

    private void initView() {
        edt_edit_resource_name = findViewById(R.id.edt_edit_resource_name);
        edt_edit_resource_description = findViewById(R.id.edt_edit_resource_description);
        imgView_edit_resource_backToDetailResource = findViewById(R.id.imgView_edit_resource_backToDetailResource);
        btn_edit_resource = findViewById(R.id.btn_edit_resource);
    }
}
