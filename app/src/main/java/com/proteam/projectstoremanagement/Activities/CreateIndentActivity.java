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
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;

    BottomNavigationItemView nav_home,nav_Individual_indent,nav_consumption,nav_boq_indent;

    int mMonth,mDay,mYear;
    Spinner spinner_contractor_name, spinner_location, spinner_sublocation;
    EditText edt_indent_date,edt_indent_workorderno;

    List contractorlist = new ArrayList();
    List location = new ArrayList();
    Map contractormap = new HashMap();
    Map locationmap = new HashMap();
    Map sublocationmap = new HashMap();


    List sublocation = new ArrayList();

    AppCompatButton btn_indent_generate;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_create);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());



        initilize();

        calllocationapi();

        spinner_contractor_name.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner_location.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner_sublocation.setOnItemSelectedListener(OnCatSpinnerCL);


    }


    private void initilize() {
        spinner_contractor_name = findViewById(R.id.spinner_contractor_name);
        spinner_location = findViewById(R.id.spinner_location);
        spinner_sublocation = findViewById(R.id.spinner_sublocation);

        edt_indent_date = findViewById(R.id.edt_indent_date);
        edt_indent_date.setOnClickListener(this);
        btn_indent_generate=findViewById(R.id.btn_indent_generate);
        btn_indent_generate.setOnClickListener(this);
        edt_indent_workorderno=findViewById(R.id.edt_indent_workorderno);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);

    }


    private void calllocationapi() {
        progressDialog=new ProgressDialog(CreateIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Constructorlocationrequest constructorlocationrequest = new Constructorlocationrequest("puma_client@gmail.com", 10);

                WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel>(CreateIndentActivity.this);
                webServices.constructorlocation(WebServices.ApiType.location, constructorlocationrequest);
            }
        }

    }

    private void callSublocationapi() {

        progressDialog=new ProgressDialog(CreateIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                SubLocationRaiseRequest subLocationRaiseRequest = new SubLocationRaiseRequest("2");

                WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel>(CreateIndentActivity.this);
                webServices.constructorSublocation(WebServices.ApiType.sublocation, subLocationRaiseRequest);
            }
        }

    }
    private void callboqupdateapi() {

        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String stor = sharedPreferences.getString("store_id",null);

        Boqrequest boqrequest = new Boqrequest(stor,"2","2");
        WebServices<Boqlist> webServices = new WebServices<Boqlist>(CreateIndentActivity.this);
        webServices.boqapi( WebServices.ApiType.boq,boqrequest );

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.nav_boq_indent:
                Intent intentIndentStatus = new Intent(CreateIndentActivity.this,IndentStatusActivity.class);
                startActivity(intentIndentStatus);
                finishAndRemoveTask();
                break;
            case R.id.nav_consumption:
                Intent intentConList = new Intent(CreateIndentActivity.this,ConsumptionListActivity.class);
                startActivity(intentConList);
                finishAndRemoveTask();
                break;
            case R.id.nav_Individual_indent:
                Intent intentIndividual = new Intent(CreateIndentActivity.this,IndividualIndentListActivity.class);
                startActivity(intentIndividual);
                finishAndRemoveTask();
                break;
            case R.id.nav_home:
                Intent intentHome = new Intent(CreateIndentActivity.this,MainActivity.class);
                startActivity(intentHome);
                finishAndRemoveTask();
                break;

            case R.id.btn_indent_generate:

                if (spinner_location.getSelectedItem() == null) {
                    Toast.makeText(this, "Select location", Toast.LENGTH_SHORT).show();
                } else if (spinner_contractor_name.getSelectedItem() == null) {

                    Toast.makeText(this, "Select the Contractor Name", Toast.LENGTH_SHORT).show();
                } else if (spinner_sublocation.getSelectedItem() == null) {

                    Toast.makeText(this, "Select sublocation", Toast.LENGTH_SHORT).show();
                } else {

                    String store;
                    String location = String.valueOf(locationmap.get(spinner_location.getSelectedItem().toString()));
                    String sublocation = String.valueOf(sublocationmap.get(spinner_sublocation.getSelectedItem().toString()));
                    String contractorname = String.valueOf(contractormap.get(spinner_contractor_name.getSelectedItem().toString()));

                    callboqupdateapi();
                    Intent intent = new Intent(CreateIndentActivity.this, RaiseIndentActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("contractor_id", contractorname);
                    bundle.putString("location_id", location);
                    bundle.putString("sublocation_id", sublocation);
                    bundle.putString("contractor_name", spinner_contractor_name.getSelectedItem().toString());
                    bundle.putString("location_name", spinner_location.getSelectedItem().toString());
                    bundle.putString("sublocation_name", spinner_sublocation.getSelectedItem().toString());
                    bundle.putString("date", edt_indent_date.getText().toString());
                    bundle.putString("workorderno", edt_indent_workorderno.getText().toString());

                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                break;


            case R.id.edt_indent_date:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateIndentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.MONTH, monthofyear);
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                edt_indent_date.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        }, mYear, mMonth, mDay);
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


    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
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

                    for(int i = 0; i<list.size(); i++ ){

                        location.add(contractorlocationmodel.getLocations().get(i).getBlock_name());
                        locationmap.put(contractorlocationmodel.getLocations().get(i).getBlock_name(), contractorlocationmodel.getLocations().get(i).getBlock_id());
                    }

                    list2 = contractorlocationmodel.getContractors();

                    for(int i = 0; i<list2.size(); i++ ){

                        contractorlist.add(contractorlocationmodel.getContractors().get(i).getFull_name());

                    }

                    ArrayAdapter adapter=new ArrayAdapter(CreateIndentActivity.this,android.R.layout.simple_list_item_1,location);
                    spinner_location.setAdapter(adapter);


                    ArrayAdapter adapte=new ArrayAdapter(CreateIndentActivity.this,android.R.layout.simple_list_item_1,contractorlist);
                    spinner_contractor_name.setAdapter(adapte);

                    callSublocationapi();
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

                    for(int i = 0; i<list.size(); i++ ){

                        sublocation.add(conSubLocationModel.getSub_locations().get(i).getLocation_name());
                        sublocationmap.put(conSubLocationModel.getSub_locations().get(i).getLocation_name(),conSubLocationModel.getSub_locations().get(i).getLocation_id());
                    }
                    ArrayAdapter adapter1=new ArrayAdapter(CreateIndentActivity.this,android.R.layout.simple_list_item_1,sublocation);
                    spinner_sublocation.setAdapter(adapter1);


                }else {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                }
                break;


        }
    }
}