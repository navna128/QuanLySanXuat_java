package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Models.Materials;
import com.example.Models.Products;
import com.example.quanlysanxuat.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    private Context mContext;
    private List<Products> productsList;

    public ProductAdapter(Context mContext, List<Products> productsList) {
        this.mContext = mContext;
        this.productsList = productsList;
    }

    public void setData(List<Products> list){
        this.productsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Products products = productsList.get(position);
        if (products == null){
            return;
        }
        Glide.with(mContext).load(products.getProductIMG()).into(holder.imgproduct);
        holder.tvName.setText(products.getProductName());
    }

    @Override
    public int getItemCount() {
        if (productsList != null){
            return productsList.size();
        }
        return 0;
    }

    public class ProductHolder extends RecyclerView.ViewHolder{

        private ImageView imgproduct;
        private TextView tvName;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            imgproduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.txtProductName);
        }
    }
}
