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
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProductAvtivity extends AppCompatActivity {
    private ImageView imgView_edit_product_backToMaterialType;
    private Button btn_edit_product;
    private EditText edt_edit_product_name, edt_edit_product_description, edt_edit_product_image;

    private Products products;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_product);

        initView();
        loadData();
        onClick();
    }

    private void onClick() {
        btn_edit_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products = new Products();
                products.setIdProduct(DataManager.selectedProduct.getIdProduct());
                products.setProductName(edt_edit_product_name.getText().toString());
                products.setProductlDescription(edt_edit_product_description.getText().toString());
                products.setProductIMG(edt_edit_product_image.getText().toString());

                ApiService.api.PutProduct(products.getIdProduct(),products).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            DataManager.selectedProduct = products;
                            for (int i = 0 ; i < DataManager.productsList.size() ; i++){
                                if (DataManager.productsList.get(i).getIdProduct().equals(DataManager.selectedProduct.getIdProduct())){
                                    DataManager.productsList.set(i,products);
                                }
                            }
                            Toast.makeText(EditProductAvtivity.this, "Sửa mẫu hàng thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DetailProductActivity.class));
                            finish();
                        }
                        else Toast.makeText(EditProductAvtivity.this, "Sửa mẫu hàng không thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        imgView_edit_product_backToMaterialType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetailProductActivity.class));
                finish();
            }
        });

    }

    private void loadData() {
        edt_edit_product_name.setText(DataManager.selectedProduct.getProductName());
        edt_edit_product_description.setText(DataManager.selectedProduct.getProductlDescription());
        edt_edit_product_image.setText(DataManager.selectedProduct.getProductIMG());
    }



    private void initView() {
        imgView_edit_product_backToMaterialType=findViewById(R.id.imgView_edit_product_backToMaterialType);
        btn_edit_product=findViewById(R.id.btn_edit_product);
        edt_edit_product_name=findViewById(R.id.edt_edit_product_name);
        edt_edit_product_description=findViewById(R.id.edt_edit_product_description);
        edt_edit_product_image= findViewById(R.id.edt_edit_product_image);
    }
}
