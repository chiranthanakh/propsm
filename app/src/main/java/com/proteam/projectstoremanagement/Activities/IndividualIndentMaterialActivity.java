package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.IndividualIndentMaterialAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.IndividualIndentMaterialModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class IndividualIndentMaterialActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

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
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.nav_home:
                Intent IntentHome = new Intent(IndividualIndentMaterialActivity.this,MainActivity.class);
                startActivity(IntentHome);
               // finishAndRemoveTask();
              finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent Intentboq = new Intent(IndividualIndentMaterialActivity.this,IndentStatusActivity.class);
                startActivity(Intentboq);
               // finishAndRemoveTask();
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent IntentIndi = new Intent(IndividualIndentMaterialActivity.this,IndividualIndentListActivity.class);
                startActivity(IntentIndi);
                //finishAndRemoveTask();
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent Intentcon = new Intent(IndividualIndentMaterialActivity.this,ConsumptionListActivity.class);
                startActivity(Intentcon);
                //finishAndRemoveTask();
                finishAffinity();
                break;
        }
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