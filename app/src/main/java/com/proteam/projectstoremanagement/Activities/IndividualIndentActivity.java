package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Model.ConSubLocationModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndividualIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    int mMonth,mDay,mYear;
    Spinner sp_indi_contractorName,sp_indi_location,sp_indi_sublocation;
    EditText edt_indi_date,edt_indi_orderNumber,edt_indi_remarks;


    List contractorlist = new ArrayList();
    List location = new ArrayList();
    Map contractormap = new HashMap();
    Map locationmap = new HashMap();
    Map sublocationmap = new HashMap();
    List sublocation = new ArrayList();


    ProgressDialog progressDialog;

    AppCompatButton btn_indi_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent);

        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();
        sp_indi_contractorName.setOnItemSelectedListener(OnCatSpinnerCL);
        sp_indi_location.setOnItemSelectedListener(OnCatSpinnerCL1);
        sp_indi_sublocation.setOnItemSelectedListener(OnCatSpinnerCL);
    }

    private void initilize()
    {
        sp_indi_contractorName=findViewById(R.id.sp_indi_contractorName);

        sp_indi_location=findViewById(R.id.sp_indi_location);

        sp_indi_sublocation=findViewById(R.id.sp_indi_sublocation);
        edt_indi_date=findViewById(R.id.edt_indi_date);
        edt_indi_date.setOnClickListener(this);
        edt_indi_orderNumber=findViewById(R.id.edt_indi_orderNumber);
        edt_indi_remarks=findViewById(R.id.edt_indi_remarks);
        btn_indi_submit=findViewById(R.id.btn_indi_submit);
        btn_indi_submit.setOnClickListener(this);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        calllocationapi();

    }

    private void calllocationapi() {
        progressDialog=new ProgressDialog(IndividualIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                SharedPreferences sharedPreferences = this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String email = sharedPreferences.getString("email", null);
                int storeid = Integer.parseInt(sharedPreferences.getString("store_id",null));

                Constructorlocationrequest constructorlocationrequest = new Constructorlocationrequest(email, storeid);

                WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel>(IndividualIndentActivity.this);
                webServices.constructorlocation(WebServices.ApiType.location, constructorlocationrequest);
            }
        }

    }

    private void callSublocationapi() {

        progressDialog=new ProgressDialog(IndividualIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                String location = String.valueOf(locationmap.get(sp_indi_location.getSelectedItem().toString()));


                SubLocationRaiseRequest subLocationRaiseRequest = new SubLocationRaiseRequest(location);

                WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel>(IndividualIndentActivity.this);
                webServices.constructorSublocation(WebServices.ApiType.sublocation, subLocationRaiseRequest);
            }
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code)
    {
        switch (URL) {
            case location:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }
                if (response != null) {

                    List list = new ArrayList();
                    List list2 = new ArrayList();
                    Contractorlocationmodel contractorlocationmodel = (Contractorlocationmodel) response;

                    list = contractorlocationmodel.getLocations();
                    location.clear();
                    locationmap.clear();
                    for(int i = 0; i<list.size(); i++ ){

                        location.add(contractorlocationmodel.getLocations().get(i).getBlock_name());
                        locationmap.put(contractorlocationmodel.getLocations().get(i).getBlock_name(), contractorlocationmodel.getLocations().get(i).getBlock_id());
                    }

                    list2 = contractorlocationmodel.getContractors();
                    contractorlist.clear();
                    for(int i = 0; i<list2.size(); i++ ){

                        contractormap.put(contractorlocationmodel.getContractors().get(i).getFull_name(),contractorlocationmodel.getContractors().get(i).getContractor_id());
                        contractorlist.add(contractorlocationmodel.getContractors().get(i).getFull_name());

                    }

                    ArrayAdapter adapter=new ArrayAdapter(IndividualIndentActivity.this,android.R.layout.simple_list_item_1,location);
                    sp_indi_location.setAdapter(adapter);


                    ArrayAdapter adapte=new ArrayAdapter(IndividualIndentActivity.this,android.R.layout.simple_list_item_1,contractorlist);
                    sp_indi_contractorName.setAdapter(adapte);

                }else {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                }
                break;

            case sublocation:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }
                if (response != null) {

                    List list = new ArrayList();
                    ConSubLocationModel conSubLocationModel = (ConSubLocationModel) response;

                    list = conSubLocationModel.getSub_locations();

                    sublocation.clear();
                    sublocationmap.clear();
                    for(int i = 0; i<list.size(); i++ ){

                        sublocation.add(conSubLocationModel.getSub_locations().get(i).getLocation_name());
                        sublocationmap.put(conSubLocationModel.getSub_locations().get(i).getLocation_name(),conSubLocationModel.getSub_locations().get(i).getLocation_id());
                    }
                    ArrayAdapter adapter1=new ArrayAdapter(IndividualIndentActivity.this,android.R.layout.simple_list_item_1,sublocation);
                    sp_indi_sublocation.setAdapter(adapter1);


                }else {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                }
                break;


        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_indi_submit:

                if (sp_indi_location.getSelectedItem() == null) {
                    Toast.makeText(this, "Select location", Toast.LENGTH_SHORT).show();
                } else if (sp_indi_contractorName.getSelectedItem() == null) {

                    Toast.makeText(this, "Select the Contractor Name", Toast.LENGTH_SHORT).show();
                } else if (sp_indi_sublocation.getSelectedItem() == null) {

                    Toast.makeText(this, "Select sublocation", Toast.LENGTH_SHORT).show();
                } else {

                    String store;
                    String location = String.valueOf(locationmap.get(sp_indi_location.getSelectedItem().toString()));
                    String sublocation = String.valueOf(sublocationmap.get(sp_indi_sublocation.getSelectedItem().toString()));
                    String contractorname = String.valueOf(contractormap.get(sp_indi_contractorName.getSelectedItem().toString()));


                    Intent intent = new Intent(IndividualIndentActivity.this, IndividualIndentMaterialActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("contractor_id", contractorname);
                    bundle.putString("location_id", location);
                    bundle.putString("sublocation_id", sublocation);
                    bundle.putString("contractor_name", sp_indi_contractorName.getSelectedItem().toString());
                    bundle.putString("location_name", sp_indi_location.getSelectedItem().toString());
                    bundle.putString("sublocation_name", sp_indi_sublocation.getSelectedItem().toString());
                    bundle.putString("date", edt_indi_date.getText().toString());
                    bundle.putString("workorderno", edt_indi_orderNumber.getText().toString());

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;

            case R.id.nav_home:
                Intent hoIntent = new Intent(IndividualIndentActivity.this,MainActivity.class);
                startActivity(hoIntent);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent boIntent = new Intent(IndividualIndentActivity.this,IndentStatusActivity.class);
                startActivity(boIntent);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent Intentindi = new Intent(IndividualIndentActivity.this,IndividualIndentListActivity.class);
                startActivity(Intentindi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent Intentcon = new Intent(IndividualIndentActivity.this,ConsumptionListActivity.class);
                startActivity(Intentcon);
                finishAffinity();
                break;


            case R.id.edt_indi_date:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(IndividualIndentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                Calendar calendar =Calendar.getInstance();
                                calendar.set(Calendar.MONTH,monthofyear);
                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                                edt_indi_date.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        },mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
        }

    }


    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);

        }

        public void onNothingSelected(AdapterView<?> parent) {
           ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
               ((TextView) parent.getChildAt(0)).setTextSize(15);
        }
    };

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL1 = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);
            callSublocationapi();

        }

        public void onNothingSelected(AdapterView<?> parent) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);
        }
    };

}