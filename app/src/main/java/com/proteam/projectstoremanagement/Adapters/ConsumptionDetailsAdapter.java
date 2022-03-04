package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.ConsumptionDetailsModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class ConsumptionDetailsAdapter extends ArrayAdapter<ConsumptionDetailsModel> {

    public ConsumptionDetailsAdapter(@NonNull Context context, ArrayList<ConsumptionDetailsModel> arrayList) {
        super(context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.consumption_details_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ConsumptionDetailsModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView consumption_De_materialCode = currentItemView.findViewById(R.id.tv_consumption_De_materialCode);
        consumption_De_materialCode.setText(currentNumberPosition.getConsumptionMaterialCode());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView consumption_De_materialName = currentItemView.findViewById(R.id.tv_consumption_De_materialName);
        consumption_De_materialName.setText(currentNumberPosition.getConsumptionMaterialName());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView consumption_De_consQty = currentItemView.findViewById(R.id.tv_consumption_De_consQty);
        consumption_De_consQty.setText(currentNumberPosition.getConsumptionQty());



        // then return the recyclable view
        return currentItemView;
    }
}
