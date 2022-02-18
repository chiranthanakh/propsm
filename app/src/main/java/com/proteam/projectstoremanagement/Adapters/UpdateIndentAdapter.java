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

import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.Model.UpdateIndentModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class UpdateIndentAdapter extends ArrayAdapter<UpdateIndentModel> {

    public UpdateIndentAdapter(@NonNull Context context, ArrayList<UpdateIndentModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.updateindent_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        UpdateIndentModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView updatematerialcode = currentItemView.findViewById(R.id.update_indent_m_code);
        updatematerialcode.setText(currentNumberPosition.getUpdatematerialcode());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView updatematerialname = currentItemView.findViewById(R.id.update_indent_m_name);
        updatematerialname.setText(currentNumberPosition.getUpdatematerialname());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView updteboqbalance = currentItemView.findViewById(R.id.update_indent_m_balanceboq);
        updteboqbalance.setText(currentNumberPosition.getUpdatebalanceboq());

        TextView updatequy = currentItemView.findViewById(R.id.update_indent_m_raiseqty);
        updatequy.setText(currentNumberPosition.getUpdateraiseqty());

        // then return the recyclable view
        return currentItemView;
    }
}
