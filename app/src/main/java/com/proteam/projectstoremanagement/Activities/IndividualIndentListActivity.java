package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Adapters.IndividualIndentListAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.IndividualIndentListModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Indentstatusrequest;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndentStatusdirectlist;
import com.proteam.projectstoremanagement.Response.IndentStatuslist;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class IndividualIndentListActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;

    FloatingActionButton fab_add_individual_indent;
    ListView lv_individual_indent_status;
    ProgressDialog progressDialog;
    final ArrayList<IndividualIndentListModel> arrayList = new ArrayList<IndividualIndentListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent_list);
        initialize();

        callapi();


    }

    private void initialize()
    {
        fab_add_individual_indent=findViewById(R.id.fab_add_individual_indent);
        fab_add_individual_indent.setOnClickListener(this);
        lv_individual_indent_status=findViewById(R.id.lv_individual_indent_status);

    }


    private void callapi() {

        progressDialog=new ProgressDialog(IndividualIndentListActivity.this);
        if(progressDialog!=null)
        {
            if(!progressDialog.isShowing())
            {

                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Indentstatusrequest indentstatusrequest = new Indentstatusrequest("71");

                WebServices<IndentStatusdirectlist> webServices = new WebServices<IndentStatusdirectlist>(IndividualIndentListActivity.this);
                webServices.indentstatusdirect(WebServices.ApiType.indentstatus,indentstatusrequest );
            }
            else {

            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_add_individual_indent:
                Intent intent = new Intent(IndividualIndentListActivity.this,IndividualIndentActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {

            case indentstatus:

                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if (response != null) {

                        List list = new ArrayList();
                        IndentStatusdirectlist indentStatusdirectlist = (IndentStatusdirectlist) response;

                        list = indentStatusdirectlist.getDirect_indent();

                        for (int i = 0; i < list.size(); i++) {

                            arrayList.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(), indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(), indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                        }

                        IndividualIndentListAdapter numbersArrayAdapter = new IndividualIndentListAdapter(this, arrayList);
                        ListView indentStatusList = findViewById(R.id.lv_individual_indent_status);
                        indentStatusList.setAdapter(numbersArrayAdapter);

                    } else {

                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }


        }
    }
}