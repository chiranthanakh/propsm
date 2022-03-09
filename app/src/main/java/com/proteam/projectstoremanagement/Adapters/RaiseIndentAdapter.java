package com.proteam.projectstoremanagement.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Utils.OnChange;

import java.util.ArrayList;

public class RaiseIndentAdapter extends ArrayAdapter<RaiseIndentModel> {

    private OnChange mCallback;


    public RaiseIndentAdapter(@NonNull Context context, ArrayList<RaiseIndentModel> arrayList,OnChange listner) {
        super(context, 0, arrayList);
        this.mCallback = listner;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.raise_indent_list_layout, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        RaiseIndentModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView raisematerialcode = currentItemView.findViewById(R.id.tv_indent_materialCode);
        raisematerialcode.setText(currentNumberPosition.getMaterialcode());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView raisematerialname = currentItemView.findViewById(R.id.tv_indent_materialName);
        raisematerialname.setText(currentNumberPosition.getMaterialname());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView raiseboqbalance = currentItemView.findViewById(R.id.tv_indent_boqBalance);
        raiseboqbalance.setText(currentNumberPosition.getBoqbalance());

        EditText raisequy = currentItemView.findViewById(R.id.edt_indent_materialRaiseQty);
        raisequy.setText(currentNumberPosition.getRaiseqty());

        LinearLayout layout = currentItemView.findViewById(R.id.data123);

        raisequy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCallback.onChange1(raisequy.getText().toString(),position,currentNumberPosition.getBoqbalance());

               // opengcadminDialog(raisequy.getText().toString());

            }
        });

        raisequy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().equals(null)){
                    Toast.makeText(getContext(), "Please enter correct login details", Toast.LENGTH_SHORT).show();
                }else {

                }



            }
        });

        // then return the recyclable view
        return currentItemView;
    }

    private void opengcadminDialog(String value) {
        final Dialog dialog =new Dialog(getContext());

        dialog.setContentView(R.layout.dialog_gcadmincount);
        dialog.show();


        EditText et_count = dialog.findViewById(R.id.edt_gc_count);
        et_count.setText(value);

        Button bt_submit = dialog.findViewById(R.id.btn_gc_submit);


        Boolean state = false;

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

    }

}
