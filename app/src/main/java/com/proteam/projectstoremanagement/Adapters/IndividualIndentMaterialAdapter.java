package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.IndividualIndentMaterialModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Utils.OnChange;
import com.proteam.projectstoremanagement.Utils.OnClick;

import java.util.ArrayList;

public class IndividualIndentMaterialAdapter extends ArrayAdapter<IndividualIndentMaterialModel> {

    private OnClick mClick;

    public IndividualIndentMaterialAdapter(@NonNull Context context, ArrayList<IndividualIndentMaterialModel> arrayList, OnClick listner) {
        super(context, 0, arrayList);
        this.mClick = listner;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.individual_indent_material, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        IndividualIndentMaterialModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView Individual_material_inNo = currentItemView.findViewById(R.id.individual_In_mCode);
        Individual_material_inNo.setText(currentNumberPosition.getMaterialCode());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView Individual_material_mName = currentItemView.findViewById(R.id.individual_In_mName);
        Individual_material_mName.setText(currentNumberPosition.getMaterialName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView Individual_material_Mqty = currentItemView.findViewById(R.id.individual_In_mQty);
        Individual_material_Mqty.setText(currentNumberPosition.getIndentQty());

        ImageView delete = currentItemView.findViewById(R.id.iv_action_edit_indent);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onClickitem(currentNumberPosition.getMaterialCode());
            }
        });


        // then return the recyclable view
        return currentItemView;
    }

    private void calldeleteapi() {


      //  WebServices<IndividualMaterialListResponse> webServices = new WebServices<IndividualMaterialListResponse>(IndividualIndentMaterialActivity.this);
       // webServices.stockmaterialname(WebServices.ApiType.IndividualMatlistName);

    }
}
