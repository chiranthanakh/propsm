package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.MenuItemCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;

import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.RaiseIndentPreview;
import com.proteam.projectstoremanagement.Request.RaiseIndentPreviewResponse;
import com.proteam.projectstoremanagement.Request.Raiseintentdataitems;
import com.proteam.projectstoremanagement.Request.Updatepreviewitems;
import com.proteam.projectstoremanagement.Request.Updatepreviewlist;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndenteditList;
import com.proteam.projectstoremanagement.Response.PreviewResponsce;
import com.proteam.projectstoremanagement.Utils.OnChange;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class RaiseIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener, OnChange {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    AppCompatButton btn_indent_preview;
    TextView tv_raise_indent_total_item,tv_contractor_name,tv_location_name,tv_sublocation_name,tv_indent_date,tv_work_order_number;
    ListView lv_raise_indent_list;
    EditText search;
    Boqlist boqlist;
    ArrayList<RaiseIndentModel> temp = new ArrayList();

    List boqcomponentslist = new ArrayList();
    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    String location,sublocation,contrctorname,storeid,location_id,sublocation_id,contrctorname_id,date,workorderno;
    ProgressDialog progressDialog;
    LinearLayout ll_no_data_raiseindent;
    IndenteditList indenteditList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_indent);
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

        initialize();

    }

    private void initialize()
    {

        tv_indent_date=findViewById(R.id.tv_indent_date);
        tv_sublocation_name=findViewById(R.id.tv_sublocation_name);
        tv_location_name=findViewById(R.id.tv_location_name);
        tv_raise_indent_total_item=findViewById(R.id.tv_raise_indent_total_item);
        btn_indent_preview=findViewById(R.id.btn_indent_preview);
        tv_contractor_name = findViewById(R.id.tv_contractor_name);
        tv_work_order_number=findViewById(R.id.tv_work_order_number);
        btn_indent_preview.setOnClickListener(this);
        lv_raise_indent_list=findViewById(R.id.lv_raise_indent_list);
        search = findViewById(R.id.edt_search12);
        ll_no_data_raiseindent = findViewById(R.id.ll_no_data_raiseindent);

        tv_contractor_name.setText(contrctorname);
        tv_location_name.setText(location);
        tv_sublocation_name.setText(sublocation);
        tv_indent_date.setText(date);
        tv_work_order_number.setText(workorderno);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);

    }


    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(RaiseIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Boqrequest boqrequest = new Boqrequest(storeid, location_id, sublocation_id,contrctorname_id);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(RaiseIndentActivity.this);
                webServices.boqapi(WebServices.ApiType.boq, boqrequest);
            }
        }

    }

    private void calleditboq(String id) {

        progressDialog=new ProgressDialog(RaiseIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                //Boqrequest boqrequest = new Boqrequest(storeid, location_id, sublocation_id);

                Indentpendingrequest indentpendingrequest = new Indentpendingrequest(id);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(RaiseIndentActivity.this);
                webServices.editboqapi(WebServices.ApiType.boqedit, indentpendingrequest);
            }
        }
    }

    private void callpreviewapi() {

        progressDialog=new ProgressDialog(RaiseIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ArrayList<Raiseintentdataitems> itemslist = new ArrayList<>();

                for(int i=0;i<arrayList.size();i++){

                    itemslist.add(new Raiseintentdataitems(arrayList.get(i).getMaterial_id(),arrayList.get(i).getRaiseqty(),arrayList.get(i).getBoqbalance()));

                }

                ArrayList<RaiseIndentPreviewResponse> locationdetail = new ArrayList<RaiseIndentPreviewResponse>();
                SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String user = sharedPreferences.getString("userid",null);

                locationdetail.add(new RaiseIndentPreviewResponse(contrctorname_id,location_id,sublocation_id,workorderno,date,storeid,user,"success"));

                RaiseIndentPreview raiseIndentPreview1 = new RaiseIndentPreview(locationdetail,itemslist);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(RaiseIndentActivity.this);
                webServices.prevewapi(WebServices.ApiType.priview, raiseIndentPreview1);
            }
        }
    }


    private void callupdatepreviewapi(String id) {

        progressDialog=new ProgressDialog(RaiseIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ArrayList<Updatepreviewitems> itemslist = new ArrayList<>();

                for(int i=0;i<arrayList.size();i++){

                    itemslist.add(new Updatepreviewitems(id,arrayList.get(i).getRaiseqty(),arrayList.get(i).getMaterial_id()));

                }
                ArrayList<RaiseIndentPreviewResponse> locationdetail = new ArrayList<RaiseIndentPreviewResponse>();
                SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String user = sharedPreferences.getString("userid",null);

               // locationdetail.add(new RaiseIndentPreviewResponse(contrctorname_id,location_id,sublocation_id,workorderno,date,storeid,user,"success"));

                Updatepreviewlist updatepreviewlist = new Updatepreviewlist(itemslist);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(RaiseIndentActivity.this);
                webServices.prevewapiupdate(WebServices.ApiType.priview, updatepreviewlist);
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        Boolean state = bundle.getBoolean("status");
        String indentid = bundle.getString("indent_id");

        if(state){
            calleditboq(indentid);
        }else {
            callboqupdateapi();
        }


        search.addTextChangedListener(new TextWatcher() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.nav_consumption:
                Intent intentConList = new Intent(RaiseIndentActivity.this,ConsumptionListActivity.class);
                startActivity(intentConList);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent intentIndividual = new Intent(RaiseIndentActivity.this,IndividualIndentListActivity.class);
                startActivity(intentIndividual);
                finishAffinity();
                break;
            case R.id.nav_home:
                Intent intentHome = new Intent(RaiseIndentActivity.this,MainActivity.class);
                startActivity(intentHome);
                finishAffinity();
                break;

            case R.id.nav_boq_indent:
                Intent intentBoq = new Intent(RaiseIndentActivity.this,IndentStatusActivity.class);
                startActivity(intentBoq);
                finishAffinity();
                break;
            case R.id.btn_indent_preview:
                //callpreviewapi();

                Bundle bundle = getIntent().getExtras();
                Boolean state = bundle.getBoolean("status");
                String indentid = bundle.getString("indent_id");

                if(state){
                    callupdatepreviewapi(indentid);
                }else {
                    callpreviewapi();
                }

                break;
        }

    }


    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {

            case boq:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        boqlist = (Boqlist) response;
                        boqcomponentslist = boqlist.getBoq_list();

                        arrayList.clear();
                        for (int i=0;i<boqcomponentslist.size();i++){

                            arrayList.add(new RaiseIndentModel(boqlist.getBoq_list().get(i).getMaterial_manual_id(),boqlist.getBoq_list().get(i).getMaterial_name(),boqlist.getBoq_list().get(i).getBalance_boq(),"0",boqlist.getBoq_list().get(i).getMaterial_id(),boqlist.getBoq_list().get(i).getBoq_closing_stock(),boqlist.getBoq_list().get(i).getBoq_issued_qty(),boqlist.getBoq_list().get(i).getBoq_consumption_qty()));

                        }

                        if(arrayList.size()==0){
                            ll_no_data_raiseindent.setVisibility(View.VISIBLE);
                        }else {

                            tv_raise_indent_total_item.setText(String.valueOf(boqcomponentslist.size()));
                            RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, arrayList,this);
                            ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);

                            pendingindentstatus.setAdapter(numbersArrayAdapter);
                        }


                    }
                }
                break;

            case priview:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

            if (isSucces) {

                if(response!=null) {

                    PreviewResponsce previewResponsce = (PreviewResponsce) response;

                    ArrayList prviewlist = new ArrayList();
                    List list = new ArrayList();

                    list = previewResponsce.getMaterial_details();

                    for (int i=0;i<list.size();i++){

                        prviewlist.add(
                                previewResponsce.getMaterial_details().get(i).getMaterial_manual_id()+"--"+
                                previewResponsce.getMaterial_details().get(i).getMaterial_name()+"--"+
                                        previewResponsce.getMaterial_details().get(i).getBoq_balance_qty()+"--"+
                                previewResponsce.getMaterial_details().get(i).getMaterial_id()+"--"+
                                        previewResponsce.getMaterial_details().get(i).getIndent_qty()+"--"+
                                        previewResponsce.getMaterial_details().get(i).getIndent_id()+"--"+
                                previewResponsce.getMaterial_details().get(i).getBoq_qty()+"--"+
                                previewResponsce.getMaterial_details().get(i).getClosing_stock());

                    }

                    Intent intent = new Intent(RaiseIndentActivity.this,UpateIndentActivity.class);

                    intent.putExtra("arraylist", prviewlist);
                    startActivity(intent);


                }
                else
                {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                }

            } else
            {
                Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }

            break;

            case boqedit:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        boqcomponentslist.clear();

                        indenteditList = (IndenteditList) response;

                        List editlist = new ArrayList();
                        editlist = indenteditList.getMaterial_details();
                        boqcomponentslist = indenteditList.getMaterial_details();

                        arrayList.clear();
                        for (int i=0;i<editlist.size();i++){

                           arrayList.add(new RaiseIndentModel(indenteditList.getMaterial_details().get(i).getMaterial_manual_id(),indenteditList.getMaterial_details().get(i).getMaterial_name(),indenteditList.getMaterial_details().get(i).getBoq_balance_qty(),indenteditList.getMaterial_details().get(i).getIndent_qty(),
                                   indenteditList.getMaterial_details().get(i).getMaterial_id(), indenteditList.getMaterial_details().get(i).getBoq_closing_stock(), indenteditList.getMaterial_details().get(i).getBoq_issued_qty(), indenteditList.getMaterial_details().get(i).getBoq_consumption_qty()));

                        }

                        if(arrayList.size()==0){
                            ll_no_data_raiseindent.setVisibility(View.VISIBLE);
                        }else {
                            ll_no_data_raiseindent.setVisibility(View.GONE);
                            tv_raise_indent_total_item.setText(String.valueOf(editlist.size()));
                            RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, arrayList,this);
                            ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
                            pendingindentstatus.setAdapter(numbersArrayAdapter);
                        }

                    }
                    else
                    {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }

                } else
                {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

    void filter(String text){

        Bundle bundle = getIntent().getExtras();
        Boolean state = bundle.getBoolean("status");
        String indentid = bundle.getString("indent_id");


        if(text.equals("")){

            RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, arrayList,this);
            ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
            pendingindentstatus.setAdapter(numbersArrayAdapter);

        }else {

            temp.clear();
            for (int i=0;i<boqcomponentslist.size();i++){
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches

                RaiseIndentModel model = arrayList.get(i);

                if(state){
                    if(indenteditList.getMaterial_details().get(i).getMaterial_name().toLowerCase().trim().contains(text.toLowerCase().trim())){
                        temp.add(new RaiseIndentModel(model.getMaterialcode(),model.getMaterialname(),model.getBoqbalance(),model.getRaiseqty(),model.getMaterial_id(),model.getClosing_stock(),model.getIssued_stock(),model.getConsumed_stock()));
                    }
                }else {
                    if(boqlist.getBoq_list().get(i).getMaterial_name().toLowerCase().trim().contains(text.toLowerCase().trim())){
                        temp.add(new RaiseIndentModel(model.getMaterialcode(),model.getMaterialname(),model.getBoqbalance(),model.getRaiseqty(),model.getMaterial_id(),model.getClosing_stock(),model.getIssued_stock(),model.getConsumed_stock()));
                    }
                }
            }

            if(temp.size()==0){
                ll_no_data_raiseindent.setVisibility(View.VISIBLE);

            }else{
                ll_no_data_raiseindent.setVisibility(View.GONE);
            }

                RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, temp,this);
                ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
                pendingindentstatus.setAdapter(numbersArrayAdapter);


        }
    }


    @Override
    public void onChange1(String value, int position,String boqvalue) {

        opengcadminDialog(value,position,boqvalue);

    }

    private void opengcadminDialog(String value,int position,String boqvalue) {
        final Dialog dialog =new Dialog(this);

        dialog.setContentView(R.layout.dialog_gcadmincount);
        dialog.show();

        EditText et_count = dialog.findViewById(R.id.edt_gc_count);
        et_count.setText(value);

        Button bt_submit = dialog.findViewById(R.id.btn_gc_submit);
        TextView textvisable = dialog.findViewById(R.id.textvisable);
        TextView tv_consume_stock = dialog.findViewById(R.id.tv_consume_stock);
        TextView tv_issued_stock = dialog.findViewById(R.id.tv_issued_stock);
        TextView tv_closing_stock = dialog.findViewById(R.id.tv_closing_stock);
        Boolean state = false;

        String data[] = boqvalue.split("--");
        tv_closing_stock.setText(data[1]);
        tv_issued_stock.setText(data[2]);
        tv_consume_stock.setText(data[3]);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(search.getText().toString().equals("")){

                    int value =Math.round(Float.parseFloat(et_count.getText().toString().trim()));

                    //if((value>Integer.valueOf(Math.round(Float.parseFloat(data[0])))) || (value==Integer.valueOf(Math.round(Float.parseFloat(data[0])))) ){
                     //   textvisable.setVisibility(View.VISIBLE);
                        //dialog.dismiss();
                       // Toast.makeText(RaiseIndentActivity.this, "Indent quantity is more than BOQ balance", Toast.LENGTH_SHORT).show();
                   // }else {
                        textvisable.setVisibility(View.GONE);
                        arrayList.set(position, new RaiseIndentModel(arrayList.get(position).getMaterialcode(),arrayList.get(position).getMaterialname(),arrayList.get(position).getBoqbalance(),et_count.getText().toString(),arrayList.get(position).getMaterial_id(),arrayList.get(position).getClosing_stock(),arrayList.get(position).getIssued_stock(),arrayList.get(position).getConsumed_stock()));

                        RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(RaiseIndentActivity.this, arrayList,RaiseIndentActivity.this);
                        ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
                        pendingindentstatus.setAdapter(numbersArrayAdapter);

                        dialog.dismiss();
                   // }

                }else {

                    int value =Math.round(Float.parseFloat(et_count.getText().toString().trim()));

                    //if((value>Integer.valueOf(Math.round(Float.parseFloat(boqvalue)))) || (value==Integer.valueOf(Math.round(Float.parseFloat(boqvalue)))) ){

                      //  textvisable.setVisibility(View.VISIBLE);
                        //dialog.dismiss();
                        //Toast.makeText(RaiseIndentActivity.this, "Indent quantity is more than BOQ balance", Toast.LENGTH_SHORT).show();

                    //}else {

                        RaiseIndentModel model = temp.get(position);
                        for(int i=0;i<arrayList.size();i++){

                            if(arrayList.get(i).getMaterialcode().equals(temp.get(position).getMaterialcode())){

                                arrayList.set(i, new RaiseIndentModel(arrayList.get(i).getMaterialcode(),arrayList.get(i).getMaterialname(),arrayList.get(i).getBoqbalance(),et_count.getText().toString(),arrayList.get(i).getMaterial_id(),arrayList.get(position).getClosing_stock(),arrayList.get(position).getIssued_stock(),arrayList.get(position).getConsumed_stock()));

                                RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(RaiseIndentActivity.this, arrayList,RaiseIndentActivity.this);
                                ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
                                pendingindentstatus.setAdapter(numbersArrayAdapter);

                                dialog.dismiss();
                            }
                        }

                    //}
                }

            }
        });

    }
}