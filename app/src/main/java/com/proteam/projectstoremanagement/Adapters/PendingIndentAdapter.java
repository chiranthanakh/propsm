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

import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.pending_indent_layout_list, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        PendingIndentModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView pendingindentnumber = currentItemView.findViewById(R.id.tv_pending_indent_number);
        pendingindentnumber.setText(currentNumberPosition.getIndentnumber());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView pendign_in_contractor = currentItemView.findViewById(R.id.tv_pending_indent_contactor);
        pendign_in_contractor.setText(currentNumberPosition.getContractorname());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView pending_indent_status = currentItemView.findViewById(R.id.pending_indent_status);
        pending_indent_status.setText(currentNumberPosition.getStatus());


        // then return the recyclable view
        return currentItemView;
    }
}
