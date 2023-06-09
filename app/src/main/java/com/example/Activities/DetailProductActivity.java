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

import com.bumptech.glide.Glide;
import com.example.API.ApiService;
import com.example.DataManager;
import com.example.Models.PrimaryUnits;
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {
    private TextView txtView_detail_product_id, txtView_detail_product_name,txtView_detail_product_description;
    private ImageView imgView_detail_back_to_product, imgView_detail_product_image;
    private Button btn_detail_delete_product, btn_detail_go_to_edit_product;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.detail_product);
        InitView();
        setData();
        onClick();
    }

    private void onClick() {
        imgView_detail_back_to_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                finish();
            }
        });
        btn_detail_delete_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailProductActivity.this)
                        .setTitle("Delete Product")
                        .setMessage("Do you want to delete product: " + DataManager.selectedProduct.getProductName() + "?")
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService.api.DeleteProduct(DataManager.selectedProduct.getIdProduct()).enqueue(new Callback<Products>() {
                                    @Override
                                    public void onResponse(Call<Products> call, Response<Products> response) {
                                        if (response.isSuccessful()){
                                            DataManager.productsList.remove(DataManager.selectedProduct);
                                            startActivity(new Intent(getApplicationContext(),ProductActivity.class));
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Products> call, Throwable t) {
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
        btn_detail_go_to_edit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditProductAvtivity.class));
                finish();
            }
        });
    }

    private void setData() {
        txtView_detail_product_id.setText(DataManager.selectedProduct.getIdProduct());
        txtView_detail_product_name.setText(DataManager.selectedProduct.getProductName());
        txtView_detail_product_description.setText(DataManager.selectedProduct.getProductlDescription());
        if (DataManager.selectedProduct.getProductIMG() != null){
            Glide.with(this).load(DataManager.selectedProduct.getProductIMG()).override(400,400).into(imgView_detail_product_image);
        }
    }

    private void InitView() {
        txtView_detail_product_id= findViewById(R.id.txtView_detail_product_id);
        txtView_detail_product_name= findViewById(R.id.txtView_detail_product_name);
        txtView_detail_product_description= findViewById(R.id.txtView_detail_product_description);
        imgView_detail_back_to_product= findViewById(R.id.imgView_detail_back_to_product);
        btn_detail_delete_product= findViewById(R.id.btn_detail_delete_product);
        btn_detail_go_to_edit_product= findViewById(R.id.btn_detail_go_to_edit_product);
        imgView_detail_product_image=findViewById(R.id.imgView_detail_product_image);
    }
}
