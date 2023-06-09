package com.example.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.API.ApiService;
import com.example.Adapter.MaterialAdapter;
import com.example.Adapter.MaterialTypeAdapter;
import com.example.Adapter.ProductAdapter;
import com.example.DataManager;
import com.example.Models.Materials;
import com.example.Models.Products;
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView rcv_product;
    private ProductAdapter productAdapter;
    private ImageView btn_product_backToHome, imgView_Add_Product;
    private EditText search_Product;
    private List<Products> productsList;
    private List<Products> All = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_product);

        All =DataManager.productsList;
        initView();
        setUpView();
        loadData();
        onClick();
    }

    private void onClick() {
        btn_product_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        imgView_Add_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddProduct.class));
                finish();
            }
        });

        productAdapter.setOnClickListener(new ProductAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                DataManager.selectedProduct = All.get(pos);
                startActivity(new Intent(getApplicationContext(), DetailProductActivity.class));
                finish();
            }
        });
        search_Product.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                productsList = new ArrayList<>();
                String productNameSearch = search_Product.getText().toString();
                for (int i = 0; i < DataManager.productsList.size(); i++){
                    if (DataManager.productsList.get(i).getProductName().toLowerCase().contains(productNameSearch.toLowerCase())){
                        productsList.add(DataManager.productsList.get(i));
                    }
                }
                All =productsList;
                productAdapter.setData(All);
            }
        });
    }

    private void loadData() {
        productAdapter.setData(DataManager.productsList);

    }

    private void setUpView() {
        productAdapter = new ProductAdapter(this, DataManager.productsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_product.setLayoutManager(linearLayoutManager);
        rcv_product.setAdapter(productAdapter);
    }

    private void initView() {
        rcv_product = findViewById(R.id.rcv_product);
        btn_product_backToHome = findViewById(R.id.btn_product_backToHome);
        imgView_Add_Product = findViewById(R.id.imgView_Add_Product);
        search_Product = findViewById(R.id.search_Product);
    }
}
