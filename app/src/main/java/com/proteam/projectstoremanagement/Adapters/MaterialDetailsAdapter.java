package com.proteam.projectstoremanagement.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proteam.projectstoremanagement.Activities.MainActivity;
import com.proteam.projectstoremanagement.Model.MaterialModel;
import com.proteam.projectstoremanagement.R;

import java.util.List;

public class MaterialDetailsAdapter extends RecyclerView.Adapter<MaterialDetailsAdapter.MyViewHolder> {

    private List<MaterialModel> materialdetails;


    public MaterialDetailsAdapter(List<MaterialModel> materialdetails) {
        this.materialdetails = materialdetails;
    }

    @NonNull
    @Override
    public MaterialDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.material_home_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialDetailsAdapter.MyViewHolder holder, int position) {
        MaterialModel materialModel = materialdetails.get(position);
        holder.material_code.setText(materialModel.getMaterialcode());
        holder.material_name.setText(materialModel.getMaterialname());
        holder.closing_stock.setText(materialModel.getClosingstock());


        holder.cc_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+materialModel.getMaterialcode(),Toast.LENGTH_LONG).show();

               /* Intent intent = new Intent(context, ReportsActivity.class);
                context.startActivity(intent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return materialdetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView material_code, material_name, closing_stock;
        public ImageView material_delete;
        public LinearLayout cc_layout;

        public MyViewHolder(@NonNull View view) {
            super(view);
            material_code = (TextView) view.findViewById(R.id.tv_material_code);
            material_name = (TextView) view.findViewById(R.id.tv_material_name);
            closing_stock = (TextView) view.findViewById(R.id.tv_closing_stock);
            material_delete = (ImageView) view.findViewById(R.id.material_delete);
            cc_layout = (LinearLayout) itemView.findViewById(R.id.cc_layout);


        }
    }

}
