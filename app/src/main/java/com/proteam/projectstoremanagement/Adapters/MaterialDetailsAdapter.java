package com.proteam.projectstoremanagement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Activities.MainActivity;
import com.proteam.projectstoremanagement.Model.MaterialSModel;
import com.proteam.projectstoremanagement.Model.MaterialStockModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Response.MaterialStockDeleteResponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;

public class MaterialDetailsAdapter extends ArrayAdapter<MaterialSModel> {
    Context context;
    public MaterialDetailsAdapter(@NonNull Context context, ArrayList<MaterialSModel> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.material_home_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        MaterialSModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView material_code = currentItemView.findViewById(R.id.tv_Stock_material_code);
        material_code.setText(currentNumberPosition.getMaterial_manual_id());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView material_name = currentItemView.findViewById(R.id.tv_Stock_material_name);
        material_name.setText(currentNumberPosition.getMaterial_name());


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView material_stock = currentItemView.findViewById(R.id.tv_Stock_closing_stock);
        material_stock.setText(currentNumberPosition.getClosing_stock());

        ImageView iv_image = currentItemView.findViewById(R.id.Stock_material_delete);

  /*    iv_image.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              WebServices<MaterialStockDeleteResponse> webServices = new WebServices<MaterialStockDeleteResponse>((OnResponseListener<MaterialStockDeleteResponse>) context);
              webServices.deleteMstockdata(WebServices.ApiType.deletestockMhome,materialStockDeleteRequest);
          }
      });*/



        // then return the recyclable view
        return currentItemView;
    }

}
