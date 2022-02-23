package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.MenuItemCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Utils.OnChange;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class RaiseIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener, OnChange {
    ImageView mToolbar;
    AppCompatButton btn_indent_preview;
    TextView tv_raise_indent_total_item,tv_contractor_name,tv_location_name,tv_sublocation_name,tv_indent_date,tv_work_order_number;
    ListView lv_raise_indent_list;
    EditText search;
    Boqlist boqlist;
    ArrayList<RaiseIndentModel> temp = new ArrayList();;

    List boqcomponentslist = new ArrayList();
    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    String location,sublocation,contrctorname,stor,location_id,sublocation_id,contrctorname_id,date,workorderno;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        stor = sharedPreferences.getString("store_id",null);


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
        search = findViewById(R.id.edt_search);

        tv_contractor_name.setText(contrctorname);
        tv_location_name.setText(location);
        tv_sublocation_name.setText(sublocation);
        tv_indent_date.setText(date);
        tv_work_order_number.setText(workorderno);
        callboqupdateapi();


    }



    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(RaiseIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                //Boqrequest boqrequest = new Boqrequest(stor,location,sublocation);

                Boqrequest boqrequest = new Boqrequest("10", "2", "2");
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(RaiseIndentActivity.this);
                webServices.boqapi(WebServices.ApiType.boq, boqrequest);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

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
            case R.id.btn_indent_preview:
                Intent intent = new Intent(RaiseIndentActivity.this,UpateIndentActivity.class);
                startActivity(intent);
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

                        for (int i=0;i<boqcomponentslist.size();i++){

                            arrayList.add(new RaiseIndentModel(boqlist.getBoq_list().get(i).getMaterial_manual_id(),boqlist.getBoq_list().get(i).getMaterial_name(),boqlist.getBoq_list().get(i).getBalance_boq(),boqlist.getBoq_list().get(i).getQty()));

                        }

                        tv_raise_indent_total_item.setText(String.valueOf(boqcomponentslist.size()));
                        RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, arrayList,this);
                        ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);

                        pendingindentstatus.setAdapter(numbersArrayAdapter);

                    }
                }

                break;

        }
    }


    void filter(String text){

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

                if(boqlist.getBoq_list().get(i).getMaterial_name().toLowerCase().trim().contains(text.toLowerCase().trim())){
                    temp.add(new RaiseIndentModel(model.getMaterialcode(),model.getMaterialname(),model.getBoqbalance(),model.getRaiseqty()));
                }
            }

            RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, temp,this);
            ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list);
            pendingindentstatus.setAdapter(numbersArrayAdapter);


        }

        //update recyclerview
       // pendingindentstatus.updateList(temp);
    }


    @Override
    public void onChange1(String value, int position) {


        if(search.getText().toString().equals("")){



        }else {

            //RaiseIndentModel model = temp.get(position);

            //if(arrayList.get())

            RaiseIndentModel model = arrayList.get(position);
            model.getMaterialcode();
            arrayList.set(position, new RaiseIndentModel(model.getMaterialcode(),model.getMaterialname(),model.getBoqbalance(),value));


        }
    }
}