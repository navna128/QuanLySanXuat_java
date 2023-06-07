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
import com.example.quanlysanxuat.R;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialHolder>{

    private Context mContext;
    private List<Materials> materialsList;

    public MaterialAdapter(Context mContext, List<Materials> materials) {
        this.mContext = mContext;
        this.materialsList = materials;
    }

    public void setData(List<Materials> list){
        this.materialsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MaterialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, parent, false);

        return new MaterialHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialHolder holder, int position) {
        Materials materials = materialsList.get(position);
        if (materials == null){
            return;
        }
        Glide.with(mContext).load(materials.getMaterialIMG()).into(holder.imgmaterial);
        holder.tvName.setText(materials.getMaterialName());
        holder.tvQuantity.setText((materials.getPrimaryQuantity()+""));
    }
    @Override
    public int getItemCount() {
        if (materialsList != null){
            return materialsList.size();
        }
        return 0;
    }

    public class MaterialHolder extends RecyclerView.ViewHolder{

        private ImageView imgmaterial;
        private TextView tvName;
        private TextView tvQuantity;
        public MaterialHolder(@NonNull View itemView) {
            super(itemView);

            imgmaterial = itemView.findViewById(R.id.imgMaterial);
            tvName = itemView.findViewById(R.id.txtMaterialName);
            tvQuantity = itemView.findViewById(R.id.txtMaterialQuantity);
        }
    }
}
