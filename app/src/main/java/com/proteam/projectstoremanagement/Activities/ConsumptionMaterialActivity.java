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
import com.proteam.projectstoremanagement.Adapters.ConsumptionMaterialAdapter;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.ConsumptionMaterialsModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.ConsumptionMaterialListRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.ConsumptionMaterialListResponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionMaterialActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    ProgressDialog progressDialog;

    AppCompatButton btn_Con_save,btn_Con_back;
    TextView tv_con_contractor_name,tv_con_locationName,tv_con_subLocationName,tv_con_workOrder,tv_con_conDate,tv_con_total_item;
    ListView lv_Con_detailsList;
    EditText ConSearch;

    String location,sublocation,contrctorname,storeid,location_id,sublocation_id,contrctorname_id,date,workorderno;


    List consumptionmaterial = new ArrayList();
    final ArrayList<ConsumptionMaterialsModel> arrayList = new ArrayList<ConsumptionMaterialsModel>();


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
        ConSearch = findViewById(R.id.edt_Con_search);
        tv_con_total_item=findViewById(R.id.tv_con_total_item);

        btn_Con_save=findViewById(R.id.btn_Con_save);
        btn_Con_back=findViewById(R.id.btn_Con_back);

        tv_con_contractor_name.setText(contrctorname);
        tv_con_subLocationName.setText(sublocation);
        tv_con_locationName.setText(location);
        tv_con_workOrder.setText(workorderno);
        tv_con_conDate.setText(date);


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

    private void callConsumptionMDetails() {

        progressDialog=new ProgressDialog(ConsumptionMaterialActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ConsumptionMaterialListRequest consumptionMaterialListRequest = new ConsumptionMaterialListRequest("1","1","1","1");
                WebServices<ConsumptionMaterialListResponse> webServices = new WebServices<ConsumptionMaterialListResponse>(ConsumptionMaterialActivity.this);
                webServices.consumptionMaDetails(WebServices.ApiType.consumptionMateriallsit, consumptionMaterialListRequest);
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

                        ConsumptionMaterialListResponse consumptionMaterialListResponse = (ConsumptionMaterialListResponse) response;
                        consumptionmaterial = consumptionMaterialListResponse.getList_of_materials();

                        arrayList.clear();
                        for (int i=0;i<consumptionmaterial.size();i++){

                            arrayList.add(new ConsumptionMaterialsModel(consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_manual_id(),consumptionMaterialListResponse.getList_of_materials().get(i).getMaterial_name(),consumptionMaterialListResponse.getList_of_materials().get(i).getIssued_qty()));

                        }
                        tv_con_total_item.setText(String.valueOf(consumptionmaterial.size()));

                        ConsumptionMaterialAdapter numbersArrayAdapter = new ConsumptionMaterialAdapter(this, arrayList,this);
                        ListView consumptionMaterialDetails = findViewById(R.id.lv_Con_detailsList);

                        consumptionMaterialDetails.setAdapter(numbersArrayAdapter);

                    }
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
        }

    }


}