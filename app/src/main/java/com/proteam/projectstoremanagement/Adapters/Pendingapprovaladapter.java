package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Utils.OnChange;

import java.util.ArrayList;

public class Pendingapprovaladapter extends ArrayAdapter {


    public Pendingapprovaladapter(@NonNull Context context, ArrayList<RaiseIndentModel> arrayList) {
        super(context, 0, arrayList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.pendingforaprovallist, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        RaiseIndentModel currentNumberPosition = (RaiseIndentModel) getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView raisematerialcode = currentItemView.findViewById(R.id.tv_indent_materialCode11);
        raisematerialcode.setText(currentNumberPosition.getMaterialcode());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView raisematerialname = currentItemView.findViewById(R.id.tv_indent_materialName11);
        raisematerialname.setText(currentNumberPosition.getMaterialname());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView raiseboqbalance = currentItemView.findViewById(R.id.tv_indent_boqBalance11);
        raiseboqbalance.setText(currentNumberPosition.getBoqbalance());

        TextView raisequy = currentItemView.findViewById(R.id.edt_indent_materialRaiseQty11);
        raisequy.setText(currentNumberPosition.getRaiseqty());

        // then return the recyclable view
        return currentItemView;
    }
}
