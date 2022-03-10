package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.ConsumptionDetailsModel;
import com.proteam.projectstoremanagement.Model.ConsumptionMaterialsModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Utils.OnChange;

import java.util.ArrayList;

public class ConsumptionMaterialAdapter extends ArrayAdapter<ConsumptionMaterialsModel> {

    private OnChange mCallback;


    public ConsumptionMaterialAdapter(@NonNull Context context, ArrayList<ConsumptionMaterialsModel> arrayList ,OnChange listner) {
        super(context, 0, arrayList);
        this.mCallback = listner;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.consumption_material_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ConsumptionMaterialsModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView consumption_Ma_materialCode = currentItemView.findViewById(R.id.tv_con_materialDeCode);
        consumption_Ma_materialCode.setText(currentNumberPosition.getMaterial_manual_id());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView consumption_Ma_materialName = currentItemView.findViewById(R.id.tv_con_materialDeName);
        consumption_Ma_materialName.setText(currentNumberPosition.getMaterial_name());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView consumption_Ma_issueQty = currentItemView.findViewById(R.id.tv_con_materialDeQty);
        consumption_Ma_issueQty.setText(currentNumberPosition.getLast_updated_qty());

        EditText editText  = currentItemView.findViewById(R.id.tv_con_materialDeConQty);
        editText.setText(currentNumberPosition.getIssued_qty());
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onChange1(editText.getText().toString(),position,currentNumberPosition.getIssued_qty());
            }
        });



        // then return the recyclable view
        return currentItemView;
    }
}
