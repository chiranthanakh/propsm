package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Adapters.PendingIndentAdapter;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.Response.PendingIndentList;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class PendingIndentListActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    LinearLayout ll_status_click;
    ListView lv_pending_indent_list;

    List pendingindentlist = new ArrayList();

    final ArrayList<PendingIndentModel> arrayList = new ArrayList<PendingIndentModel>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_indent_list);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        initilize();


    }

    private void initilize()
    {
        lv_pending_indent_list=findViewById(R.id.lv_pending_indent_list);
        callpendingindentapi();


    }

    private void callpendingindentapi() {
        progressDialog=new ProgressDialog(PendingIndentListActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                PendingIndentRequest pendingIndentRequest = new PendingIndentRequest("72");
                WebServices<PendingIndentList> webServices = new WebServices<PendingIndentList>(PendingIndentListActivity.this);
                webServices.pendingindent(WebServices.ApiType.pendingindent, pendingIndentRequest);
            }

        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
        /*    case R.id.ll_status_click:
                Intent intent = new Intent(PendingIndentListActivity.this,PendingIndentActivity.class);
                startActivity(intent);
                break;*/
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {

            case pendingindent:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        PendingIndentList pendingIndentModel = (PendingIndentList) response;
                        pendingindentlist = pendingIndentModel.getIndent_list();

                        for (int i=0;i<pendingindentlist.size();i++){

                            arrayList.add(new PendingIndentModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus()));

                        }

                     //   tv_raise_indent_total_item.setText(String.valueOf(boqcomponentslist.size()));

                        // Now create the instance of the NumebrsViewAdapter and pass
                        // the context and arrayList created above
                        PendingIndentAdapter numbersArrayAdapter = new PendingIndentAdapter(this, arrayList);

                        // create the instance of the ListView to set the numbersViewAdapter
                        ListView pendingindentlist = findViewById(R.id.lv_pending_indent_list);

                        // set the numbersViewAdapter for ListView
                        pendingindentlist.setAdapter(numbersArrayAdapter);

                    }
                    else{
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }



                break;


        }


    }
}