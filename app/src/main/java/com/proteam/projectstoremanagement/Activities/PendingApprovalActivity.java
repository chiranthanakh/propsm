package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.Pendingapprovaladapter;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.Pendingapprovelistupdaterequest;
import com.proteam.projectstoremanagement.Request.RaiseIndentPreviewResponse;
import com.proteam.projectstoremanagement.Request.Updatepreviewitems;
import com.proteam.projectstoremanagement.Request.Updatepreviewlist;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndenteditList;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class PendingApprovalActivity extends AppCompatActivity implements OnResponseListener {

    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;
    ProgressDialog progressDialog;

    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    String location,sublocation,contrctorname,date,workorderno,location_id,sublocation_id,contrctorname_id,indentid;
    TextView tv_indent_date,tv_sublocation_name,tv_location_name,tv_contractor_name,tv_work_order_number;
    Button btn_indent_conform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_approval);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        Bundle bundle = getIntent().getExtras();
        location_id = bundle.getString("location_id");
        sublocation_id =bundle.getString("sublocation_id");
        contrctorname_id = bundle.getString("contractor_id");
        location = bundle.getString("location_name");
        sublocation =bundle.getString("sublocation_name");
        contrctorname = bundle.getString("contractor_name");
        date = bundle.getString("date");
        workorderno=bundle.getString("workorderno");
        indentid = bundle.getString("indent_id");
        initilize();

    }

    private void initilize() {

        tv_indent_date=findViewById(R.id.tv_indent_date11);
        tv_sublocation_name=findViewById(R.id.tv_sublocation_name11);
        tv_location_name=findViewById(R.id.tv_location_name11);
        btn_indent_conform=findViewById(R.id.btn_indent_preview11);
        tv_contractor_name = findViewById(R.id.tv_contractor_name11);
        tv_work_order_number=findViewById(R.id.tv_work_order_number11);

        tv_contractor_name.setText(contrctorname);
        tv_location_name.setText(location);
        tv_sublocation_name.setText(sublocation);
        tv_indent_date.setText(date);
        tv_work_order_number.setText(workorderno);

        btn_indent_conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callaproverapi(indentid);
            }
        });


        callboqupdateapi();
    }

    private void callboqupdateapi() {

        Bundle bundle = getIntent().getExtras();
        Boolean state = bundle.getBoolean("status");
        String indentid = bundle.getString("indent_id");

        progressDialog=new ProgressDialog(PendingApprovalActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Indentpendingrequest indentpendingrequest = new Indentpendingrequest(indentid);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(PendingApprovalActivity.this);
                webServices.pendingapproval(WebServices.ApiType.boq, indentpendingrequest);
            }
        }

    }

    private void callaproverapi(String id) {

        progressDialog=new ProgressDialog(PendingApprovalActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ArrayList<Updatepreviewitems> itemslist = new ArrayList<>();

                for(int i=0;i<arrayList.size();i++){

                    itemslist.add(new Updatepreviewitems(id,arrayList.get(i).getRaiseqty(),arrayList.get(i).getMaterial_id()));

                }

                SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String user = sharedPreferences.getString("userid",null);

                // locationdetail.add(new RaiseIndentPreviewResponse(contrctorname_id,location_id,sublocation_id,workorderno,date,storeid,user,"success"));

                Pendingapprovelistupdaterequest updatepreviewlist = new Pendingapprovelistupdaterequest(itemslist);
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(PendingApprovalActivity.this);
                webServices.approveupdate(WebServices.ApiType.send, updatepreviewlist);
            }
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

                        IndenteditList indenteditList = (IndenteditList) response;

                        List editlist = new ArrayList();
                        editlist = indenteditList.getMaterial_details();

                        arrayList.clear();
                        for (int i=0;i<editlist.size();i++){

                            arrayList.add(new RaiseIndentModel(indenteditList.getMaterial_details().get(i).getMaterial_manual_id(),indenteditList.getMaterial_details().get(i).getMaterial_name(),indenteditList.getMaterial_details().get(i).getBoq_balance_qty(),indenteditList.getMaterial_details().get(i).getIndent_qty(),indenteditList.getMaterial_details().get(i).getMaterial_id()));

                        }

                       // tv_raise_indent_total_item.setText(String.valueOf(editlist.size()));
                        Pendingapprovaladapter numbersArrayAdapter = new Pendingapprovaladapter(this, arrayList);
                        ListView pendingindentstatus = findViewById(R.id.lv_raise_indent_list11);

                        pendingindentstatus.setAdapter(numbersArrayAdapter);


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

            case send:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        Generalresponce generalresponce = (Generalresponce) response;
                        Toast.makeText(this, generalresponce.getStatus(), Toast.LENGTH_SHORT).show();

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
}