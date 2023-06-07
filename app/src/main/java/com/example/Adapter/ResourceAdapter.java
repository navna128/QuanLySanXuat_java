package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.MaterialTypes;
import com.example.Models.PrimaryUnits;
import com.example.Models.Resources;
import com.example.quanlysanxuat.R;

import java.util.List;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceHolder> {

    private Context mContext;

    private List<Resources> resourcesList;
    private ResourceAdapter.onItemClickListener listener;
    public ResourceAdapter(Context mContext, List<Resources> resourcesList) {
        this.mContext = mContext;
        this.resourcesList = resourcesList;
    }
    public interface onItemClickListener {
        void onItemClick(int pos, View view);
    }
    public void setOnClickListener(ResourceAdapter.onItemClickListener listener) {
        this.listener = listener;
    }
    public void setData(List<Resources> list){
        this.resourcesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResourceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resource, parent, false);
        return new ResourceHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceHolder holder, int position) {
        Resources resources = resourcesList.get(position);
        if (resources == null){
            return;
        }
        holder.tvName.setText(resources.getResourceName());
        holder.tvDescription.setText((resources.getResourcelDescription()));
    }

    @Override
    public int getItemCount() {
        if (resourcesList != null){
            return  resourcesList.size();
        }
        return 0;
    }

    public class ResourceHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDescription;
        public ResourceHolder(@NonNull View itemView, ResourceAdapter.onItemClickListener listener) {
            super(itemView);

            tvName = itemView.findViewById(R.id.txtResourceName);
            tvDescription = itemView.findViewById(R.id.txtResourceDescription);

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
