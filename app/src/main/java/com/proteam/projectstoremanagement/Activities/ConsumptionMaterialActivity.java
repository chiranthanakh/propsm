package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.ConsumptionMaterialAdapter;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.ConsumptionMaterialsModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.ConsumptionMaterialListRequest;
import com.proteam.projectstoremanagement.Request.Raiseintentdataitems;
import com.proteam.projectstoremanagement.Request.SaveConsumptionLists;
import com.proteam.projectstoremanagement.Request.Saveconsuptionmaterialdetails;
import com.proteam.projectstoremanagement.Request.Saveconsuptionstoredetails;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.ConsumptionMaterialListResponse;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Utils.OnChange;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionMaterialActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener, OnChange {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    ProgressDialog progressDialog;

    AppCompatButton btn_Con_save,btn_Con_back;
    TextView tv_con_contractor_name,tv_con_locationName,tv_con_subLocationName,tv_con_workOrder,tv_con_conDate,tv_con_total_item;
    ListView lv_Con_detailsList;
    EditText edt_consume_search;
    String location,sublocation,contrctorname,storeid,location_id,sublocation_id,contrctorname_id,date,workorderno;


    List consumptionmaterial = new ArrayList();
    final ArrayList<ConsumptionMaterialsModel> arrayList = new ArrayList<ConsumptionMaterialsModel>();
    final ArrayList<ConsumptionMaterialsModel> temp = new ArrayList<ConsumptionMaterialsModel>();
    ConsumptionMaterialListResponse consumptionMaterialListResponse;

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

        tv_con_conDate=findViewById(R.id.tv_con_conDate);
        tv_con_subLocationName=findViewById(R.id.tv_con_subLocationName);
        tv_con_locationName=findViewById(R.id.tv_con_locationName);
        tv_con_contractor_name = findViewById(R.id.tv_con_contractor_name);
        tv_con_workOrder=findViewById(R.id.tv_con_workOrder);
        lv_Con_detailsList=findViewById(R.id.lv_Con_detailsList);

        tv_con_total_item=findViewById(R.id.tv_con_total_item);
        edt_consume_search = findViewById(R.id.edt_consume_search);
        btn_Con_save=findViewById(R.id.btn_Con_save);
        btn_Con_back=findViewById(R.id.btn_Con_back);
        tv_con_contractor_name.setText(contrctorname);
        tv_con_subLocationName.setText(sublocation);
        tv_con_locationName.setText(location);
        tv_con_workOrder.setText(workorderno);
        tv_con_conDate.setText(date);
        btn_Con_save.setOnClickListener(this);
       // String text = search.getText().toString();

       // For Navigation
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);

        callConsumptionMDetails();

    }

    //Text listner

    @Override
    protected void onResume() {
        super.onResume();

        edt_consume_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

    }

    private void callConsumptionMDetails() {

        progressDialog=new ProgressDialog(ConsumptionMaterialActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ConsumptionMaterialListRequest consumptionMaterialListRequest = new ConsumptionMaterialListRequest(storeid,location_id,sublocation_id,contrctorname_id);
                WebServices<ConsumptionMaterialListResponse> webServices = new WebServices<ConsumptionMaterialListResponse>(ConsumptionMaterialActivity.this);
                webServices.consumptionMaDetails(WebServices.ApiType.consumptionMateriallsit, consumptionMaterialListRequest);
            }
        }

    }

    private void callConsumtionsaveapi() {

        progressDialog=new ProgressDialog(ConsumptionMaterialActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ArrayList<Saveconsuptionmaterialdetails> materiallist = new ArrayList<>();

                for(int i=0;i<arrayList.size();i++){

                    materiallist.add(new Saveconsuptionmaterialdetails(arrayList.get(i).getMaterial_id(),arrayList.get(i).getIssued_qty()));

                }

                ArrayList<Saveconsuptionstoredetails> contractorstoredetails = new ArrayList<>();

                SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String user = sharedPreferences.getString("userid",null);


                contractorstoredetails.add(new Saveconsuptionstoredetails(contrctorname_id,location_id,sublocation_id,date,user));

                SaveConsumptionLists saveConsumptionLists = new SaveConsumptionLists(contractorstoredetails,materiallist);
                WebServices<ConsumptionMaterialListResponse> webServices = new WebServices<ConsumptionMaterialListResponse>(ConsumptionMaterialActivity.this);
                webServices.consumptionupdate(WebServices.ApiType.general, saveConsumptionLists);
            }
        }

    }




    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code)
    {
        switch (URL)
        {
            case consumptionMateriallsit:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                         consumptionMaterialListResponse = (ConsumptionMaterialListResponse) response;
                        consumptionmaterial = consumptionMaterialListResponse.getList_of_materials();

                        arrayList.clear();
                        for (int i=0;i<consumptionmaterial.size();i++){

                            arrayList.add(new ConsumptionMaterialsModel(consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_manual_id(),
                                    consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_name(),"0",
                                    consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_id(),
                                    consumptionMaterialListResponse.getList_of_materials().get(i).getLast_updated_qty()));

                        }
                        tv_con_total_item.setText(String.valueOf(consumptionmaterial.size()));

                       ConsumptionMaterialAdapter numbersArrayAdapter = new ConsumptionMaterialAdapter(this, arrayList,this);
                        ListView consumptionMaterialDetails = findViewById(R.id.lv_Con_detailsList);
                        consumptionMaterialDetails.setAdapter(numbersArrayAdapter);

                    }else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }
                break;

            case general:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        Generalresponce generalresponce = new Generalresponce();
                        Toast.makeText(this, generalresponce.getStatus(), Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }
                break;
        }
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

            case R.id.btn_Con_save:
                callConsumtionsaveapi();
                break;
        }

    }

    @Override
    public void onChange1(String value, int position,String boqvalue) {
        opengcadminDialog(value,position);

    }

    private void opengcadminDialog(String value,int position) {
        final Dialog dialog =new Dialog(this);
        dialog.setContentView(R.layout.dialog_gcadmincount);
        dialog.show();
        LinearLayout ll_layout = dialog.findViewById(R.id.ll_layout);
        ll_layout.setVisibility(View.GONE);
        EditText et_count = dialog.findViewById(R.id.edt_gc_count);
        et_count.setText(value);
        Button bt_submit = dialog.findViewById(R.id.btn_gc_submit);
        Boolean state = false;
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sdsd = edt_consume_search.getText().toString();
                if(edt_consume_search.getText().toString().trim().equals("")){

                    arrayList.set(position, new ConsumptionMaterialsModel(arrayList.get(position).getMaterial_manual_id(),
                            arrayList.get(position).getMaterial_name(),et_count.getText().toString(),arrayList.get(position).getMaterial_id(),arrayList.get(position).getLast_updated_qty()));

                    ConsumptionMaterialAdapter numbersArrayAdapter = new ConsumptionMaterialAdapter(ConsumptionMaterialActivity.this, arrayList,ConsumptionMaterialActivity.this);
                    ListView consumptionMaterialDetails = findViewById(R.id.lv_Con_detailsList);
                    consumptionMaterialDetails.setAdapter(numbersArrayAdapter);
                    dialog.dismiss();

                }else {
                    ConsumptionMaterialsModel model = temp.get(position);
                    for(int i=0;i<arrayList.size();i++){

                        if(arrayList.get(i).getMaterial_name().equals(temp.get(position).getMaterial_name())){

                            arrayList.set(i, new ConsumptionMaterialsModel(arrayList.get(position).getMaterial_manual_id(),
                                    arrayList.get(position).getMaterial_name(),et_count.getText().toString(),arrayList.get(position).getMaterial_id(),arrayList.get(position).getLast_updated_qty()));

                            ConsumptionMaterialAdapter numbersArrayAdapter = new ConsumptionMaterialAdapter(ConsumptionMaterialActivity.this, arrayList,ConsumptionMaterialActivity.this);
                            ListView consumptionMaterialDetails = findViewById(R.id.lv_Con_detailsList);
                            consumptionMaterialDetails.setAdapter(numbersArrayAdapter);
                            dialog.dismiss();

                        }
                    }
                }


                /*else {

                    RaiseIndentModel model = temp.get(position);

                    for(int i=0;i<arrayList.size();i++){

                        if(arrayList.get(i).getMaterialcode().equals(temp.get(position).getMaterialcode())){

                            arrayList.set(i, new RaiseIndentModel(arrayList.get(i).getMaterialcode(),arrayList.get(i).getMaterialname(),arrayList.get(i).getBoqbalance(),et_count.getText().toString(),arrayList.get(i).getMaterial_id()));

                            RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(RaiseIndentActivity.this, arrayList,RaiseIndentActivity.this);
                            ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
                            pendingindentstatus.setAdapter(numbersArrayAdapter);

                            dialog.dismiss();
                        }
                    }
                }*/

            }
        });

    }

    void filter(String text){

        Bundle bundle = getIntent().getExtras();
        Boolean state = bundle.getBoolean("status");
        String indentid = bundle.getString("indent_id");


        if(text.equals("")){

            ConsumptionMaterialAdapter numbersArrayAdapter = new ConsumptionMaterialAdapter(this, arrayList,this);
            ListView consumptionMaterialDetails = findViewById(R.id.lv_Con_detailsList);
            consumptionMaterialDetails.setAdapter(numbersArrayAdapter);

        }else {

            temp.clear();
            for (int i=0;i<consumptionmaterial.size();i++){
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches

                ConsumptionMaterialsModel model = arrayList.get(i);

                    if(consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_name().toLowerCase().trim().contains(text.toLowerCase().trim())){
                        temp.add(new ConsumptionMaterialsModel(consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_manual_id(),
                                consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_name(),"0",
                                consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_id(),
                                consumptionMaterialListResponse.getList_of_materials().get(i).getLast_updated_qty()));
                }
            }

            if(temp.size()==0){
                //ll_no_data_raiseindent.setVisibility(View.VISIBLE);
            }else{
               // ll_no_data_raiseindent.setVisibility(View.GONE);
            }

            ConsumptionMaterialAdapter numbersArrayAdapter = new ConsumptionMaterialAdapter(this, temp,this);
            ListView consumptionMaterialDetails = findViewById(R.id.lv_Con_detailsList);
            consumptionMaterialDetails.setAdapter(numbersArrayAdapter);

        }
    }


}