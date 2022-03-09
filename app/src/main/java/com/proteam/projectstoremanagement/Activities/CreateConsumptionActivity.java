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

public class CreateConsumptionActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    Spinner sp_con_contractorname,sp_con_location,sp_con_sublocation;
    EditText edt_con_date,edt_con_ordernum,edt_con_remarks;
    AppCompatButton btn_con_generate;
    int mMonth,mDay,mYear;

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
        setContentView(R.layout.activity_create_consumption);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();
        sp_con_contractorname.setOnItemSelectedListener(OnCatSpinnerCL);
        sp_con_location.setOnItemSelectedListener(OnCatSpinnerCL1);
        sp_con_sublocation.setOnItemSelectedListener(OnCatSpinnerCL);
    }


    private void initilize()
    {
        sp_con_contractorname=findViewById(R.id.sp_con_contractorname1);
        sp_con_location=findViewById(R.id.sp_con_location1);
        sp_con_sublocation=findViewById(R.id.sp_con_sublocation1);

        edt_con_date=findViewById(R.id.edt_con_date1);
        edt_con_date.setOnClickListener(this);
        edt_con_ordernum=findViewById(R.id.edt_con_ordernum1);
        edt_con_remarks=findViewById(R.id.edt_con_remarks1);

        btn_con_generate=findViewById(R.id.btn_con_generate1);
        btn_con_generate.setOnClickListener(this);

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
        progressDialog=new ProgressDialog(CreateConsumptionActivity.this);

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

                WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel>(CreateConsumptionActivity.this);
                webServices.constructorlocation(WebServices.ApiType.location, constructorlocationrequest);
            }
        }

    }

    private void callSublocationapi() {

        progressDialog=new ProgressDialog(CreateConsumptionActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                String location = String.valueOf(locationmap.get(sp_con_location.getSelectedItem().toString()));


                SubLocationRaiseRequest subLocationRaiseRequest = new SubLocationRaiseRequest(location);

                WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel>(CreateConsumptionActivity.this);
                webServices.constructorSublocation(WebServices.ApiType.sublocation, subLocationRaiseRequest);
            }
        }

    }
    /*private void callboqupdateapi() {

        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String stor = sharedPreferences.getString("store_id",null);

        Boqrequest boqrequest = new Boqrequest(stor,"2","2");
        WebServices<Boqlist> webServices = new WebServices<Boqlist>(CreateConsumptionActivity.this);
        webServices.boqapi( WebServices.ApiType.boq,boqrequest );

    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.nav_home:
                Intent intentHome = new Intent(CreateConsumptionActivity.this,MainActivity.class);
                startActivity(intentHome);
                finishAffinity();
            break;
            case R.id.nav_boq_indent:
                Intent intentboq = new Intent(CreateConsumptionActivity.this,IndentStatusActivity.class);
                startActivity(intentboq);
                finishAffinity();
            break;
            case R.id.nav_Individual_indent:
                Intent intentIndi = new Intent(CreateConsumptionActivity.this,IndividualIndentListActivity.class);
                startActivity(intentIndi);
                finishAffinity();
            break;
            case R.id.nav_consumption:
                Intent intentCList = new Intent(CreateConsumptionActivity.this,ConsumptionListActivity.class);
                startActivity(intentCList);
                finishAffinity();
                break;
            /*case R.id.btn_con_generate1:
                Intent intentconsave = new Intent(CreateConsumptionActivity.this,ConsumptionMaterialActivity.class);
                startActivity(intentconsave);
                break;*/

            case R.id.edt_con_date1:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateConsumptionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                Calendar calendar =Calendar.getInstance();
                                calendar.set(Calendar.MONTH,monthofyear);
                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                                edt_con_date.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        },mYear, mMonth, mDay);
                datePickerDialog.show();
                break;

            case R.id.btn_con_generate1:

                if (sp_con_location.getSelectedItem() == null) {
                    Toast.makeText(this, "Select location", Toast.LENGTH_SHORT).show();
                } else if (sp_con_contractorname.getSelectedItem() == null) {

                    Toast.makeText(this, "Select the Contractor Name", Toast.LENGTH_SHORT).show();
                } else if (sp_con_sublocation.getSelectedItem() == null) {

                    Toast.makeText(this, "Select sublocation", Toast.LENGTH_SHORT).show();
                } else {

                    String store;
                    String location = String.valueOf(locationmap.get(sp_con_location.getSelectedItem().toString()));
                    String sublocation = String.valueOf(sublocationmap.get(sp_con_sublocation.getSelectedItem().toString()));
                    String contractorname = String.valueOf(contractormap.get(sp_con_contractorname.getSelectedItem().toString()));


                    Intent intent = new Intent(CreateConsumptionActivity.this, ConsumptionMaterialActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("contractor_id", contractorname);
                    bundle.putString("location_id", location);
                    bundle.putString("sublocation_id", sublocation);
                    bundle.putString("contractor_name", sp_con_contractorname.getSelectedItem().toString());
                    bundle.putString("location_name", sp_con_location.getSelectedItem().toString());
                    bundle.putString("sublocation_name", sp_con_sublocation.getSelectedItem().toString());
                    bundle.putString("date", edt_con_date.getText().toString());
                    bundle.putString("workorderno", edt_con_ordernum.getText().toString());

                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                break;


            case R.id.edt_indent_date:
                final Calendar c1 = Calendar.getInstance();
                mYear = c1.get(Calendar.YEAR);
                mMonth = c1.get(Calendar.MONTH);
                mDay = c1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog1 = new DatePickerDialog(CreateConsumptionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.MONTH, monthofyear);
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                edt_con_date.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog1.show();

                break;
        }


    }

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);

        }

        public void onNothingSelected(AdapterView<?> parent) {
           // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
           // ((TextView) parent.getChildAt(0)).setTextSize(15);
        }
    };

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL1 = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);
            callSublocationapi();

        }

        public void onNothingSelected(AdapterView<?> parent) {
           // ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            //((TextView) parent.getChildAt(0)).setTextSize(15);
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

                    ArrayAdapter adapter=new ArrayAdapter(CreateConsumptionActivity.this,android.R.layout.simple_list_item_1,location);
                    sp_con_location.setAdapter(adapter);


                    ArrayAdapter adapte=new ArrayAdapter(CreateConsumptionActivity.this,android.R.layout.simple_list_item_1,contractorlist);
                    sp_con_contractorname.setAdapter(adapte);

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
                    ArrayAdapter adapter1=new ArrayAdapter(CreateConsumptionActivity.this,android.R.layout.simple_list_item_1,sublocation);
                    sp_con_sublocation.setAdapter(adapter1);


                }else {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                }
                break;


        }

    }
}