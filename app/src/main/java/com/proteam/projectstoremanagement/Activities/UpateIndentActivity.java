package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Adapters.UpdateIndentAdapter;

import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.NotificationPart.RequestNotification;
import com.proteam.projectstoremanagement.NotificationPart.SendNotificatiponmodel;
import com.proteam.projectstoremanagement.NotificationPart.Senddata;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.ConformRaiseindentresponse;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.LoginResponse;
import com.proteam.projectstoremanagement.Response.PsmDataStatusHome;
import com.proteam.projectstoremanagement.Response.RaiseIndentConfirmRequest;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.Utilities;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class UpateIndentActivity extends AppCompatActivity implements OnResponseListener, View.OnClickListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;
    private SwipeRefreshLayout sc_update_indent;
    ListView lv_update_indent_list;
    LinearLayout ll_no_data_updateIndent;
    TextView tv_total_item;
    AppCompatButton btn_indent_confirm,btn_edit,btn_indent_back;
    String indentid;

    List boqcomponentslist = new ArrayList();
    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    ProgressDialog progressDialog;
    String location_id,sublocation_id,store_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upate_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());


        ArrayList list = (ArrayList) getIntent().getSerializableExtra("arraylist");

        if(list.size()!=0){

            String[] data1 = String.valueOf(list.get(0)).split("--");

            indentid = data1[5];

            for (int i=0;i<list.size();i++){

                String[] data = String.valueOf(list.get(i)).split("--");

                arrayList.add(new RaiseIndentModel(data[0],data[1],data[2],data[4],data[3]));

            }

        }




        Bundle bundle = getIntent().getExtras();
         location_id = bundle.getString("location_id");
         sublocation_id =bundle.getString("sublocation_id");
         store_id = bundle.getString("storeid");

        initilize();

        sc_update_indent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                startActivity(getIntent());

                // Your code here
                Toast.makeText(getApplicationContext(), "Refreshing!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        // mSwipeRefreshLayout.setRefreshing(false);
                        /*callboqupdateapi();
                        callboqupdateapi();*/
                    }
                }, 3000); // Delay in millis
            }
        });

    }

    private void initilize() {
        ll_no_data_updateIndent=findViewById(R.id.ll_no_data_updateIndent);
        sc_update_indent = (SwipeRefreshLayout) findViewById(R.id.sc_update_indent);
        btn_indent_back=findViewById(R.id.btn_indent_back);
        btn_indent_back.setOnClickListener(this);
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);

        tv_total_item=findViewById(R.id.tv_update_indent_total_item);
        btn_edit = findViewById(R.id.btn_indent_edit);
        btn_edit.setOnClickListener(this);
        lv_update_indent_list=findViewById(R.id.lv_update_indent_list);
        btn_indent_confirm=findViewById(R.id.btn_indent_confirm);
        btn_indent_confirm.setOnClickListener(this);
        callboqupdateapi();


    }

    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(UpateIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Boqrequest boqrequest = new Boqrequest(store_id,location_id,sublocation_id);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(UpateIndentActivity.this);
                webServices.boqapi(WebServices.ApiType.boq, boqrequest);
            }
        }

    }


    private void callConfirmApi() {

        progressDialog=new ProgressDialog(UpateIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                RaiseIndentConfirmRequest raiseIndentConfirmRequest = new RaiseIndentConfirmRequest(indentid);
                WebServices<Generalresponce> webServices = new WebServices<Generalresponce>(UpateIndentActivity.this);
                webServices.confirmRaiseIndent(WebServices.ApiType.confirmRaiseIndent,raiseIndentConfirmRequest);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.nav_home:
                Intent navintent= new Intent(UpateIndentActivity.this,MainActivity.class);
                startActivity(navintent);
                finishAffinity();
                break;
            case R.id. nav_boq_indent:
                Intent boquintent = new Intent(UpateIndentActivity.this,IndentStatusActivity.class);
                startActivity(boquintent);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent indIntent = new Intent(UpateIndentActivity.this,IndividualIndentListActivity.class);
                startActivity(indIntent);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent conIntent= new Intent(UpateIndentActivity.this,ConsumptionListActivity.class);
                startActivity(conIntent);
                finishAffinity();
                break;
            case R.id.btn_indent_confirm:

                   callConfirmApi();
                break;

            case R.id.btn_indent_edit:
                Intent intent= new Intent(UpateIndentActivity.this,RaiseIndentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("status",true);
                bundle.putString("indent_id",indentid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.btn_indent_back:
                Intent intent_back = new Intent(UpateIndentActivity.this,IndentStatusActivity.class);
                startActivity(intent_back);
                finish();
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

                        Boqlist boqlist = (Boqlist) response;
                        boqcomponentslist = boqlist.getBoq_list();

                        for (int i=0;i<boqcomponentslist.size();i++){

                           // arrayList.add(new RaiseIndentModel(boqlist.getBoq_list().get(i).getMaterial_manual_id(),boqlist.getBoq_list().get(i).getMaterial_name(),boqlist.getBoq_list().get(i).getBalance_boq(),boqlist.getBoq_list().get(i).getQty()));

                        }
                        if(arrayList.size()==0){
                            ll_no_data_updateIndent.setVisibility(View.VISIBLE);
                        }else {

                            tv_total_item.setText(String.valueOf(boqcomponentslist.size()));
                            UpdateIndentAdapter numbersArrayAdapter = new UpdateIndentAdapter(this, arrayList);
                            ListView updateindentList = findViewById(R.id.lv_update_indent_list);
                            updateindentList.setAdapter(numbersArrayAdapter);
                        }
                    }

                }
                break;

            case confirmRaiseIndent:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {


                        ConformRaiseindentresponse generalresponce = (ConformRaiseindentresponse) response;
                        Toast.makeText(this, generalresponce.getStatus(), Toast.LENGTH_SHORT).show();
                        if(generalresponce.getStatus().equalsIgnoreCase("sucess")){
                            notifiy(generalresponce.getApprover_id(),generalresponce.getMessage());

                            Intent intent = new Intent(this,IndentStatusActivity.class);
                            startActivity(intent);
                            finish();
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

    private void notifiy(String id,String msg){

        SendNotificatiponmodel sendNotificatiponmodel = new SendNotificatiponmodel(msg,"Pro Psm","OPEN_ACTIVITY_1");
        Senddata senddata = new Senddata(msg,"PSM Raised");
        RequestNotification requestNotification = new RequestNotification();
        requestNotification.setSendNotificatiponmodel(sendNotificatiponmodel);
        requestNotification.setSenddata(senddata);
        requestNotification.setToken("/topics/"+id);

        WebServices<LoginResponse> webServices = new WebServices<LoginResponse>(UpateIndentActivity.this);
        webServices.notificationapi(Utilities.getBaseURL(UpateIndentActivity.this), WebServices.ApiType.noti,requestNotification );

    }
}