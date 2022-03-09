package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Activities.PendingApprovalActivity;
import com.proteam.projectstoremanagement.Activities.RaiseIndentActivity;
import com.proteam.projectstoremanagement.Activities.UpateIndentActivity;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class IndentStatusAdapter extends ArrayAdapter<IndentStatusModel> {

    public IndentStatusAdapter(@NonNull Context context, ArrayList<IndentStatusModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.indent_status_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        IndentStatusModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView in_status_indentnumber = currentItemView.findViewById(R.id.in_status_indentnumber);
        in_status_indentnumber.setText(currentNumberPosition.getIndentnumber());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView in_status_contractor = currentItemView.findViewById(R.id.in_status_contractor);
        in_status_contractor.setText(currentNumberPosition.getContractorname());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView in_status = currentItemView.findViewById(R.id.in_status);
        in_status.setText(currentNumberPosition.getStatus());

        ImageView iv_action_edit_indent = currentItemView.findViewById(R.id.iv_action_edit_indent);

        iv_action_edit_indent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(currentNumberPosition.getStatus().contains("Approved")){

                    Intent intent = new Intent(getContext(), PendingApprovalActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("status",true);
                    bundle.putString("indent_id",currentNumberPosition.getIndent_id());
                    bundle.putString("location_name",currentNumberPosition.getLocation_name());
                    bundle.putString("sublocation_name",currentNumberPosition.getSub_location_name());
                    bundle.putString("contractor_name",currentNumberPosition.getContractorname());
                    bundle.putString("date",currentNumberPosition.getIndent_date());
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);

                }else {

                    Intent intent = new Intent(getContext(),RaiseIndentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("status",true);
                    bundle.putString("location_name",currentNumberPosition.getLocation_name());
                    bundle.putString("sublocation_name",currentNumberPosition.getSub_location_name());
                    bundle.putString("contractor_name",currentNumberPosition.getContractorname());
                    bundle.putString("date",currentNumberPosition.getIndent_date());
                    bundle.putString("indent_id",currentNumberPosition.getIndent_id());
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);

                }

            }
        });


        if(currentNumberPosition.getStatus().contains("Pending")){
            in_status.setTextColor(Color.parseColor("#ffcc80"));
        }else if(currentNumberPosition.getStatus().contains("Approved")){
            in_status.setTextColor(Color.parseColor("#81c784"));
        }else  if(currentNumberPosition.getStatus().contains("Rejected")){
            in_status.setTextColor(Color.parseColor("#e57373"));
        }else  if(currentNumberPosition.getStatus().contains("InProgress")){
            in_status.setTextColor(Color.parseColor("#81c784"));
        }
        else if(currentNumberPosition.getStatus().equalsIgnoreCase("Issued"))
        {
            in_status.setTextColor(Color.parseColor("#696969"));
        }

        // then return the recyclable view
        return currentItemView;
    }
}
