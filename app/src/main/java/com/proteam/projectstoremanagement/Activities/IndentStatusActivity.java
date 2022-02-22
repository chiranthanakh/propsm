package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.Indentstatusrequest;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndentStatuslist;
import com.proteam.projectstoremanagement.Response.Indentstatusitems;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.Utils.Utilities;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class IndentStatusActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    DrawerLayout drawer_layout;

    FloatingActionButton fab_add_raise;
    ListView indent_status_listView;
    ProgressDialog progressDialog;
    final ArrayList<IndentStatusModel> arrayList = new ArrayList<IndentStatusModel>();
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_status);
        mToolbar = findViewById(R.id.back_toolbar);

        initialize();

        callapi();

    }


    private void initialize()
    {
        fab_add_raise=findViewById(R.id.fab_add_raise);
        fab_add_raise.setOnClickListener(this);
        indent_status_listView=findViewById(R.id.indent_status_listView);
        mToolbar.setOnClickListener(this);
        drawer_layout=findViewById(R.id.drawer_layout);
    }


    private void callapi() {

        progressDialog=new ProgressDialog(IndentStatusActivity.this);
        if(progressDialog!=null)
        {
            if(!progressDialog.isShowing())
            {

                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                Indentstatusrequest indentstatusrequest = new Indentstatusrequest("71");

                WebServices<IndentStatuslist> webServices = new WebServices<IndentStatuslist>(IndentStatusActivity.this);
                webServices.indentstatus(WebServices.ApiType.indentstatus,indentstatusrequest );
            }
            else {

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_add_raise:
                Intent intent = new Intent(IndentStatusActivity.this,CreateIndentActivity.class);
                startActivity(intent);
                break;
            case R.id.back_toolbar:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {
            case indentstatus:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null){

                         list = new ArrayList();
                        IndentStatuslist indentStatuslist = (IndentStatuslist) response;

                        list = indentStatuslist.getBoq_indent();

                        for (int i = 0; i<list.size(); i++){

                            arrayList.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));
                        }

                        IndentStatusAdapter numbersArrayAdapter = new IndentStatusAdapter(this, arrayList);
                        ListView indentStatusList = findViewById(R.id.indent_status_listView);
                        indentStatusList.setAdapter(numbersArrayAdapter);

                    }else {

                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }


                break;

        }

        }
}