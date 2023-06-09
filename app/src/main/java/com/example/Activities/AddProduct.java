package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProduct extends AppCompatActivity {
    private ImageView btn_add_product_backToMaterialType;
    private Button btn_create_product;
    private EditText edt_add_product_name, edt_add_product_description;

    private Products products;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_product);

        initView();
        onClick();
    }

    private void onClick() {
        btn_add_product_backToMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                finish();
            }
        });

        btn_create_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products = new Products();
                UUID uuid = UUID.randomUUID();
                String id= uuid.toString().substring(0,10);
                products.setIdProduct(id);
                products.setProductName(edt_add_product_name.getText().toString());
                products.setProductlDescription(edt_add_product_description.getText().toString());

                ApiService.api.PostProduct(products).enqueue(new Callback<Products>() {
                    @Override
                    public void onResponse(Call<Products> call, Response<Products> response) {
                        if (response.body()!=null) {
                            DataManager.productsList.add(products);
                            startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Products> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void initView() {
        btn_add_product_backToMaterialType=findViewById(R.id.btn_add_product_backToMaterialType);
        btn_create_product = findViewById(R.id.btn_create_product);
        edt_add_product_name = findViewById(R.id.edt_add_product_name);
        edt_add_product_description = findViewById(R.id.edt_add_product_description);
    }
}
