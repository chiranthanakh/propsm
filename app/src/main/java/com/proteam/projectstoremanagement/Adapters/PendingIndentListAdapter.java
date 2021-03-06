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

import com.proteam.projectstoremanagement.Activities.PendingIndentActivity;
import com.proteam.projectstoremanagement.Model.PendingIndentListModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class PendingIndentListAdapter extends ArrayAdapter<PendingIndentListModel> {


    public PendingIndentListAdapter(@NonNull Context context, ArrayList<PendingIndentListModel> arrayList, String status) {
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.pending_indent_layout_list, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        PendingIndentListModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView pendingindentnumber = currentItemView.findViewById(R.id.tv_pending_indent_number);
        pendingindentnumber.setText(currentNumberPosition.getIndentnumber());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView pendign_in_contractor = currentItemView.findViewById(R.id.tv_pending_indent_contactor);
        pendign_in_contractor.setText(currentNumberPosition.getContractorname());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView pending_indent_status = currentItemView.findViewById(R.id.pending_indent_status);
        pending_indent_status.setText(currentNumberPosition.getStatus());

        ImageView iv_image = currentItemView.findViewById(R.id.iv_action_view);


        if(currentNumberPosition.getStatus().equalsIgnoreCase("Pending")){
            pending_indent_status.setTextColor(Color.parseColor("#ffcc80"));
            iv_image.setVisibility(View.VISIBLE);
        }else if(currentNumberPosition.getStatus().equalsIgnoreCase("Approved")){
            pending_indent_status.setTextColor(Color.parseColor("#81c784"));
            iv_image.setVisibility(View.VISIBLE);
        }else  if(currentNumberPosition.getStatus().equalsIgnoreCase("Rejected")){
            pending_indent_status.setTextColor(Color.parseColor("#e57373"));
            iv_image.setVisibility(View.VISIBLE);
        }else  if(currentNumberPosition.getStatus().equalsIgnoreCase("InProgress")){
            pending_indent_status.setTextColor(Color.parseColor("#81c784"));
            iv_image.setVisibility(View.VISIBLE);
        }else if(currentNumberPosition.getStatus().equalsIgnoreCase("Issued"))
        {
            pending_indent_status.setTextColor(Color.parseColor("#696969"));
            iv_image.setVisibility(View.VISIBLE);
        }

        View finalCurrentItemView = currentItemView;
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent1 = new Intent(getContext(), PendingIndentActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("indentid",currentNumberPosition.getId());
                bundle1.putString("status",currentNumberPosition.getStatus());
                intentEvent1.putExtras(bundle1);
                finalCurrentItemView.getContext().startActivity(intentEvent1);

            }
        });

            // then return the recyclable view
        return currentItemView;
    }
}
