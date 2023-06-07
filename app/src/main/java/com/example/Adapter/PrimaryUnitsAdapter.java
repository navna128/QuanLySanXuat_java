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
import com.example.quanlysanxuat.R;

import java.util.List;

public class PrimaryUnitsAdapter extends RecyclerView.Adapter<PrimaryUnitsAdapter.PrimaryUnitsHolser> {


    private Context mContext;

    private List<PrimaryUnits> primaryUnitsList;

    public PrimaryUnitsAdapter(Context mContext, List<PrimaryUnits> primaryUnitsList) {
        this.mContext = mContext;
        this.primaryUnitsList = primaryUnitsList;
    }

    public void setData(List<PrimaryUnits> list){
        this.primaryUnitsList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PrimaryUnitsHolser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_primary_unit, parent, false);
        return new PrimaryUnitsHolser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrimaryUnitsHolser holder, int position) {
        PrimaryUnits primaryUnits = primaryUnitsList.get(position);
        if (primaryUnits == null){
            return;
        }
        holder.tvName.setText(primaryUnits.getPrimaryUnitName());
        holder.tvDescription.setText((primaryUnits.getPrimaryUnitlDescription()));
    }

    @Override
    public int getItemCount() {
        if (primaryUnitsList != null){
            return  primaryUnitsList.size();
        }
        return 0;
    }

    public class PrimaryUnitsHolser extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvDescription;

        public PrimaryUnitsHolser(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.txtPrimaryUnitName);
            tvDescription = itemView.findViewById(R.id.txtPrimaryUnitDescription);
        }
    }
}
