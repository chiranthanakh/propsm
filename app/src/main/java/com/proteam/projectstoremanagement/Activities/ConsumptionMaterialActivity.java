package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Response.Boqlist;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionMaterialActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    List boqcomponentslist = new ArrayList();
    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    String location,sublocation,contrctorname,storeid,location_id,sublocation_id,contrctorname_id,date,workorderno;
    ProgressDialog progressDialog;

    AppCompatButton btn_indent_preview;
    TextView tv_raise_indent_total_item,tv_contractor_name,tv_location_name,tv_sublocation_name,tv_indent_date,tv_work_order_number;
    ListView lv_raise_indent_list;
    EditText search;
    Boqlist boqlist;
    ArrayList<RaiseIndentModel> temp = new ArrayList();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_material);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        storeid = sharedPreferences.getString("store_id",null);


        Bundle bundle = getIntent().getExtras();
        location_id = bundle.getString("location_id");
        sublocation_id =bundle.getString("sublocation_id");
        contrctorname_id = bundle.getString("contractor_id");
        location = bundle.getString("location_name");
        sublocation =bundle.getString("sublocation_name");
        contrctorname = bundle.getString("contractor_name");
        date = bundle.getString("date");
        workorderno=bundle.getString("workorderno");


        initilize();
    }
    private void initilize()
    {
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);


        tv_indent_date=findViewById(R.id.tv_indent_date);
        tv_sublocation_name=findViewById(R.id.tv_sublocation_name);
        tv_location_name=findViewById(R.id.tv_location_name);
        tv_raise_indent_total_item=findViewById(R.id.tv_raise_indent_total_item);
        btn_indent_preview=findViewById(R.id.btn_indent_preview);
        tv_contractor_name = findViewById(R.id.tv_contractor_name);
        tv_work_order_number=findViewById(R.id.tv_work_order_number);
       // btn_indent_preview.setOnClickListener(this);
        lv_raise_indent_list=findViewById(R.id.lv_raise_indent_list);
        search = findViewById(R.id.edt_search);

       // tv_contractor_name.setText(contrctorname);
        //tv_location_name.setText(location);
        //tv_sublocation_name.setText(sublocation);
        //tv_indent_date.setText(date);
        //tv_work_order_number.setText(workorderno);


        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.nav_home:
                Intent intenthome = new Intent(ConsumptionMaterialActivity.this,MainActivity.class);
                startActivity(intenthome);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent intent_boq = new Intent(ConsumptionMaterialActivity.this,IndentStatusActivity.class);
                startActivity(intent_boq);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent intent_indi = new Intent(ConsumptionMaterialActivity.this,IndividualIndentListActivity.class);
                startActivity(intent_indi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent intent_com = new Intent(ConsumptionMaterialActivity.this,ConsumptionListActivity.class);
                startActivity(intent_com);
                finishAffinity();
                break;
        }

    }
}