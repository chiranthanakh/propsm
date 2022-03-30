package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.PendingIndentAdapter;
import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.NotificationPart.RequestNotification;
import com.proteam.projectstoremanagement.NotificationPart.SendNotificatiponmodel;
import com.proteam.projectstoremanagement.NotificationPart.Senddata;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.PendingIntentupdaterequest;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.Indentpending;
import com.proteam.projectstoremanagement.Response.LoginResponse;
import com.proteam.projectstoremanagement.Response.PendingIntentgenaralresponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.Utilities;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class PendingIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;

    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;
    LinearLayout ll_no_data_pending_indent;
    TextView tv_p_indent_number,tv_p_contractorName,tv_p_locationName,tv_p_sublocationName,
            tv_p_workordernumber,tv_p_status,tv_p_indentdate,tv_pending_indent_total_item;

    ProgressDialog progressDialog;
    ListView lv_pending_indent;
    Button btn_approve,btn_reject,btn_Indent_approved,btn_Indent_rejected,btn_Indent_issued,btn_Indent_InProgress;
    String id,status;
    EditText remarks;
    LinearLayout pendinglayout;
    Indentpending indentpending;

    List PendingIndent = new ArrayList();
    final ArrayList<PendingIndentModel> arrayList = new ArrayList<PendingIndentModel>();
    List indentids = new ArrayList();

    Context context=this;

    String userid;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                if (key.equals("click_action")) {
                    //perform the action you want to do with the key.
                }
            }
        }
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("indentid");
        status = bundle.getString("status");



        initilze();


    }

    private void initilze()
    {
        ll_no_data_pending_indent = findViewById(R.id.ll_no_data_pending_indent);
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setVisibility(View.GONE);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_Individual_indent.setVisibility(View.GONE);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setVisibility(View.GONE);
        nav_consumption.setOnClickListener(this);

        tv_pending_indent_total_item=findViewById(R.id.tv_pending_indent_total_item);

        lv_pending_indent=findViewById(R.id.lv_pending_indent);

        tv_p_indent_number=findViewById(R.id.tv_p_indent_number);
        tv_p_contractorName=findViewById(R.id.tv_p_contractorName);
        tv_p_locationName=findViewById(R.id.tv_p_locationName);
        tv_p_sublocationName=findViewById(R.id.tv_p_sublocationName);
        tv_p_workordernumber=findViewById(R.id.tv_p_workordernumber);
        btn_Indent_InProgress = findViewById(R.id.btn_Indent_InProgress);
        btn_Indent_approved = findViewById(R.id.btn_Indent_approved);
        pendinglayout = findViewById(R.id.pending);
        btn_Indent_rejected = findViewById(R.id.btn_Indent_rejected);
        btn_Indent_issued = findViewById(R.id.btn_Indent_issued);
        tv_p_status=findViewById(R.id.tv_p_status);
        remarks = findViewById(R.id.et_remarks1);
        tv_p_indentdate=findViewById(R.id.tv_p_indentdate);
        btn_approve = findViewById(R.id.btn_approve);
        btn_reject = findViewById(R.id.btn_reject);
        btn_reject.setOnClickListener(this);
        btn_approve.setOnClickListener(this);

        if(status.equalsIgnoreCase("Pending")){
           pendinglayout.setVisibility(View.VISIBLE);
            remarks.setVisibility(View.VISIBLE);
        }else if(status.equalsIgnoreCase("Approved")){
            btn_Indent_approved.setVisibility(View.VISIBLE);
        }else  if(status.equalsIgnoreCase("Rejected")){
           btn_Indent_rejected.setVisibility(View.VISIBLE);
        }else  if(status.equalsIgnoreCase("InProgress")){
           btn_Indent_InProgress.setVisibility(View.VISIBLE);
        }else if(status.equalsIgnoreCase("Issued"))
        {
            btn_Indent_issued.setVisibility(View.VISIBLE);
        }

        callboqupdateapi();
    }


    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(PendingIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();



                Indentpendingrequest indentpendingrequest = new Indentpendingrequest(id);
                WebServices<Indentpending> webServices = new WebServices<Indentpending>(PendingIndentActivity.this);
                webServices.pendingIndentsingle(WebServices.ApiType.pendingindentsignle, indentpendingrequest);
            }
        }

    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.nav_home:
                Intent Intenthome = new Intent(PendingIndentActivity.this,MainActivity.class);
                startActivity(Intenthome);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent Intentboq = new Intent(PendingIndentActivity.this,IndentStatusActivity.class);
                startActivity(Intentboq);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent Intentindi = new Intent(PendingIndentActivity.this,IndividualIndentListActivity.class);
                startActivity(Intentindi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent IntentCon = new Intent(PendingIndentActivity.this,ConsumptionListActivity.class);
                startActivity(IntentCon);
                finishAffinity();
                break;

            case R.id.btn_approve:

                openDialog2("Approved");
               // callupdateapi("Approved");

                break;

            case R.id.btn_reject:

                if (remarks.length() == 0) {
                    Toast.makeText(this, "Please enter the remarks", Toast.LENGTH_SHORT).show();
                   // remarks.setError("Please enter the remarks");
                }
                else
                {
                    openDialogReject("Rejected");
                }

               // callupdateapi("Rejected");

                break;

        }


    }

    private void callupdateapi(String status) {

        progressDialog=new ProgressDialog(PendingIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                PendingIntentupdaterequest pendingIntentupdaterequest = new PendingIntentupdaterequest(indentpending.getIndent_list().get(0).getIndent_id(),status,remarks.getText().toString());
                WebServices<Generalresponce> webServices = new WebServices<Generalresponce>(PendingIndentActivity.this);
                webServices.pendingintentupdate(WebServices.ApiType.general, pendingIntentupdaterequest);


            }
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL) {

            case pendingindentsignle:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                         indentpending = (Indentpending) response;
                        PendingIndent = indentpending.getIndent_boq_list();

                        arrayList.clear();

                        for (int i=0;i<PendingIndent.size();i++){

                            arrayList.add(new PendingIndentModel(indentpending.getIndent_boq_list().get(i).getMaterial_manual_id(),indentpending.getIndent_boq_list().get(i).getMaterial_name(),indentpending.getIndent_boq_list().get(i).getBalance_boq(),indentpending.getIndent_boq_list().get(i).getIndent_qty(),indentpending.getIndent_boq_list().get(i).getBoq_closing_stock(),indentpending.getIndent_boq_list().get(i).getBoq_net_issue(),indentpending.getIndent_boq_list().get(i).getBoq_consumption_qty()));

                            indentids.add(indentpending.getIndent_list().get(0).getIndent_id());

                        }

                        if(arrayList.size()==0){
                            ll_no_data_pending_indent.setVisibility(View.VISIBLE);
                        }else {

                            tv_pending_indent_total_item.setText(String.valueOf(PendingIndent.size()));

                            // the context and arrayList created above
                            PendingIndentAdapter numbersArrayAdapter = new PendingIndentAdapter(this, arrayList);

                            // create the instance of the ListView to set the numbersViewAdapter
                            ListView pendingindent = findViewById(R.id.lv_pending_indent);

                            // set the numbersViewAdapter for ListView
                            pendingindent.setAdapter(numbersArrayAdapter);

                            tv_p_indent_number.setText(indentpending.getIndent_list().get(0).getIndent_auto_gen_id());
                            tv_p_contractorName.setText(indentpending.getIndent_list().get(0).getContractor_name());
                            tv_p_locationName.setText(indentpending.getIndent_list().get(0).getLocation_name());
                            tv_p_sublocationName.setText(indentpending.getIndent_list().get(0).getSub_location_name());
                            tv_p_workordernumber.setText(indentpending.getIndent_list().get(0).getWork_order_no());
                            tv_p_status.setText(indentpending.getIndent_list().get(0).getStatus());
                            tv_p_indentdate.setText(indentpending.getIndent_list().get(0).getIndent_date());
                        }

                    }

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


                        //Generalresponce generalresponce = (Generalresponce) response;
                        PendingIntentgenaralresponse generalresponse = (PendingIntentgenaralresponse) response;

                        generalresponse.getRiser_id();

                        if(generalresponse.getStatus().equalsIgnoreCase("sucess")){

                            notifiy(generalresponse.getRiser_id(),generalresponse.getMessage());

                            finish();

                        }else {
                            Toast.makeText(this, generalresponse.getStatus(), Toast.LENGTH_SHORT).show();
                        }



                    }else {

                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }

                break;

        }
    }

    private void notifiy(String id,String msg){

        SendNotificatiponmodel sendNotificatiponmodel = new SendNotificatiponmodel(msg,"Pro Psm","OPEN_ACTIVITY_2");
        Senddata senddata = new Senddata(msg,"PSM approved");
        RequestNotification requestNotification = new RequestNotification();
        requestNotification.setSendNotificatiponmodel(sendNotificatiponmodel);
        requestNotification.setSenddata(senddata);
        requestNotification.setToken("/topics/"+id);

        WebServices<LoginResponse> webServices = new WebServices<LoginResponse>(PendingIndentActivity.this);
        webServices.notificationapi(Utilities.getBaseURL(PendingIndentActivity.this), WebServices.ApiType.noti,requestNotification );

    }

    public void openDialog2(String it) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage("Are You Sure Want to Approve?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                callupdateapi(it);
                dialog.cancel();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();


            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


    public void openDialogReject(String re) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage("Are You Sure Want to Reject?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                callupdateapi(re);
                dialog.cancel();


            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();


            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}