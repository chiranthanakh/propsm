package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.proteam.projectstoremanagement.Adapters.IndividualIndentMaterialAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.IndividualIndentMaterialModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class IndividualIndentMaterialActivity extends AppCompatActivity {
    ImageView mToolbar;
    Spinner sp_indi_material;
    ListView lv_individual_in_material;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent_material);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();
        sp_indi_material.setOnItemSelectedListener(OnCatSpinnerCL);


        final ArrayList<IndividualIndentMaterialModel> arrayList = new ArrayList<IndividualIndentMaterialModel>();

        arrayList.add(new IndividualIndentMaterialModel("1","Mondal Enterprises","Pending"));
        arrayList.add(new IndividualIndentMaterialModel("2","Bright Energy","Pending"));
        arrayList.add(new IndividualIndentMaterialModel("3","Shree Krishna Precast","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("4","Bright Energy","Rejected"));
        arrayList.add(new IndividualIndentMaterialModel("5","Mondal Enterprises","Rejected"));
        arrayList.add(new IndividualIndentMaterialModel("6","Shree Krishna Precast","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("7","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("8","Bright Energy","Pending"));
        arrayList.add(new IndividualIndentMaterialModel("9","Mondal Enterprises","Rejected"));
        arrayList.add(new IndividualIndentMaterialModel("10","Shree Krishna Precast","Rejected"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("11","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentMaterialModel("6666","Mondal Enterprises","Approved"));

        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        IndividualIndentMaterialAdapter numbersArrayAdapter = new IndividualIndentMaterialAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView indentStatusList = findViewById(R.id.lv_individual_in_material);

        // set the numbersViewAdapter for ListView
        indentStatusList.setAdapter(numbersArrayAdapter);

    }

    private void initilize()
    {
        sp_indi_material=findViewById(R.id.sp_indi_material);
        lv_individual_in_material=findViewById(R.id.lv_individual_in_material);
    }

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(14);

        }

        public void onNothingSelected(AdapterView<?> parent) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(14);
        }
    };
}