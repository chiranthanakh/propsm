package com.proteam.projectstoremanagement.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.MaterialSModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Utils.OnClick;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;

public class MaterialDetailsAdapter extends ArrayAdapter<MaterialSModel> {
    Context context;

    private OnClick mClick;


    public MaterialDetailsAdapter(@NonNull Context context, ArrayList<MaterialSModel> arrayList, OnClick listner) {
        super(context, 0, arrayList);
        this.mClick = listner;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.material_home_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        MaterialSModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView material_code = currentItemView.findViewById(R.id.tv_Stock_material_code);
        material_code.setText(currentNumberPosition.getMaterial_manual_id());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView material_name = currentItemView.findViewById(R.id.tv_Stock_material_name);
        material_name.setText(currentNumberPosition.getMaterial_name());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView material_stock = currentItemView.findViewById(R.id.tv_Stock_closing_stock);
        material_stock.setText(currentNumberPosition.getClosing_stock());

        ImageView iv_image = currentItemView.findViewById(R.id.Stock_material_delete);

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              mClick.onClickitem(currentNumberPosition.getFavorite_id());


            }
        });


        // then return the recyclable view
        return currentItemView;
    }

    /*public void openDialogReject(String de) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage("Are You Sure Want to Delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

               mClick.onClickitem();
                dialog.cancel();


            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();


            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }*/

}
