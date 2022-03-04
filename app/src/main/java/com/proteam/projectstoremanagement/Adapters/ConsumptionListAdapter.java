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

import com.proteam.projectstoremanagement.Activities.ConsumptionDetailsActivity;
import com.proteam.projectstoremanagement.Activities.PendingIndentActivity;
import com.proteam.projectstoremanagement.Model.ConsumptionListModel;
import com.proteam.projectstoremanagement.Model.PendingIndentListModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class ConsumptionListAdapter extends ArrayAdapter<ConsumptionListModel> {

    public ConsumptionListAdapter(@NonNull Context context, ArrayList<ConsumptionListModel> arrayList) {
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.consumption_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ConsumptionListModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView consumption_ConName = currentItemView.findViewById(R.id.tv_consumption_ConDate);
        consumption_ConName.setText(currentNumberPosition.getContractorName());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView consumption_LocName = currentItemView.findViewById(R.id.tv_consumption_LocName);
        consumption_LocName.setText(currentNumberPosition.getLocation());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView consumption_ConDate = currentItemView.findViewById(R.id.tv_consumption_ConDate);
        consumption_ConDate.setText(currentNumberPosition.getConsDate());

        ImageView iv_consumption_action = currentItemView.findViewById(R.id.iv_consumption_Action_view);


        View finalCurrentItemView = currentItemView;
        iv_consumption_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEvent1 = new Intent(getContext(), ConsumptionDetailsActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("siteConsumptionID",currentNumberPosition.getSiteConsumption_id());
                intentEvent1.putExtras(bundle1);
                finalCurrentItemView.getContext().startActivity(intentEvent1);

            }
        });

        // then return the recyclable view
        return currentItemView;
    }
}
