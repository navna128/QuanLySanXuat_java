package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.MaterialTypes;
import com.example.Models.Materials;
import com.example.quanlysanxuat.R;

import java.util.List;

public class MaterialTypeAdapter extends RecyclerView.Adapter<MaterialTypeAdapter.MaterialTypeHolder>{
    private Context mContext;

    private List<MaterialTypes> materialTypesList;
    private MaterialTypeAdapter.onItemClickListener listener;

    public MaterialTypeAdapter(Context mContext, List<MaterialTypes> materialTypesList) {
        this.mContext = mContext;
        this.materialTypesList = materialTypesList;
    }
    public interface onItemClickListener {
        void onItemClick(int pos, View view);
    }
    public void setOnClickListener(MaterialTypeAdapter.onItemClickListener listener) {
        this.listener = listener;
    }
    public void setData(List<MaterialTypes> list){
        this.materialTypesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MaterialTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material_type, parent, false);
        return new MaterialTypeHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialTypeHolder holder, int position) {
        MaterialTypes materialTypes = materialTypesList.get(position);
        if (materialTypes == null){
            return;
        }
        holder.tvName.setText(materialTypes.getTypeName());
        holder.tvDescription.setText((materialTypes.getTypelDescription()));
    }

    @Override
    public int getItemCount() {
        if (materialTypesList != null){
            return  materialTypesList.size();
        }
        return 0;
    }

    public class MaterialTypeHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDescription;

        public MaterialTypeHolder(@NonNull View itemView, MaterialTypeAdapter.onItemClickListener listener) {
            super(itemView);

            tvName = itemView.findViewById(R.id.txtMaterialTypeName);
            tvDescription = itemView.findViewById(R.id.txtMaterialTypeDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, v);
                        }
                    }
                }
            });
        }
    }
}
