package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.IndividualIndentListModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class IndividualIndentListAdapter extends ArrayAdapter<IndividualIndentListModel> {

    public IndividualIndentListAdapter(@NonNull Context context, ArrayList<IndividualIndentListModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.individual_indent_status_list, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        IndividualIndentListModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView Individual_InNo = currentItemView.findViewById(R.id.tv_individual_indentNO);
        Individual_InNo.setText(currentNumberPosition.getIndividualIndentNo());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView Individual_CName = currentItemView.findViewById(R.id.tv_individual_indent_Cname);
        Individual_CName.setText(currentNumberPosition.getIndividualcontractorName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView Individual_IStatus = currentItemView.findViewById(R.id.tv_individual_indent_Status);
        Individual_IStatus.setText(currentNumberPosition.getIndividualIndentStatus());


        if(currentNumberPosition.getIndividualIndentStatus().equalsIgnoreCase("Pending")){
            Individual_IStatus.setTextColor(Color.parseColor("#ffcc80"));
        }else if(currentNumberPosition.getIndividualIndentStatus().equalsIgnoreCase("Approved")){
            Individual_IStatus.setTextColor(Color.parseColor("#81c784"));
        }else  if(currentNumberPosition.getIndividualIndentStatus().equalsIgnoreCase("Rejected")){
            Individual_IStatus.setTextColor(Color.parseColor("#e57373"));
        }
        else  if(currentNumberPosition.getIndividualIndentStatus().equalsIgnoreCase("InProgress"))
        {
            Individual_IStatus.setTextColor(Color.parseColor("#81c784"));
        }


        // then return the recyclable view
        return currentItemView;
    }
}
