package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.PendingIndentListModel;
import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class PendingIndentAdapter extends ArrayAdapter<PendingIndentModel> {

    public PendingIndentAdapter(@NonNull Context context, ArrayList<PendingIndentModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.pending_indent_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        PendingIndentModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView pendign_in_materialcode = currentItemView.findViewById(R.id.p_material_code);
        pendign_in_materialcode.setText(currentNumberPosition.getPMaterialcode());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView pendign_in_materialName = currentItemView.findViewById(R.id.p_material_name);
        pendign_in_materialName.setText(currentNumberPosition.getPMaterialName());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView pendign_in_Balanceboq = currentItemView.findViewById(R.id.p_balance_boq);
        pendign_in_Balanceboq.setText(currentNumberPosition.getPBalanceboq());

        TextView pendign_in_raisedqty = currentItemView.findViewById(R.id.p_raised_qty);
        pendign_in_raisedqty.setText(currentNumberPosition.getPRaisedqty());


        // then return the recyclable view
        return currentItemView;
    }
}


