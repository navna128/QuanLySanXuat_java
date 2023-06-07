package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.ApiService;
import com.example.Adapter.MaterialAdapter;
import com.example.Adapter.ProductAdapter;
import com.example.DataManager;
import com.example.Models.Materials;
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView rcv_product;
    private ProductAdapter productAdapter;
    private List<Products> productsList;
    private ImageView btn_product_backToHome, imgView_Add_Product;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product);


        initView();
        setUpView();
        loadData();
        onClick();
    }

    private void onClick() {
        btn_product_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });

        imgView_Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddProduct.class));
                finish();
            }
        });

        productAdapter.setOnClickListener(new ProductAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DataManager.selectedProduct=productsList.get(pos);
                startActivity(new Intent(getApplicationContext(),DetailProductActivity.class));
                finish();
            }
        });
    }

    private void loadData() {
        ApiService.api.GetProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                productsList.addAll(response.body());
                productAdapter.setData(productsList);
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }

    private void setUpView() {
        productsList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_product.setLayoutManager(linearLayoutManager);
        rcv_product.setAdapter(productAdapter);
    }

    private void initView() {
        rcv_product = findViewById(R.id.rcv_product);
        btn_product_backToHome = findViewById(R.id.btn_product_backToHome);
        imgView_Add_Product = findViewById(R.id.imgView_Add_Product);
    }
}
