package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Adapters.PendingIndentListAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.Loginmodel;
import com.proteam.projectstoremanagement.Model.PendingIndentListModel;
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
    ImageView mToolbar,iv_indent_filter;


    FloatingActionButton fab_add_raise;
    ListView indent_status_listView;
    ProgressDialog progressDialog;
    final ArrayList<IndentStatusModel> arrayList = new ArrayList<IndentStatusModel>();
    List list;

    String userid;
    SharedPreferences.Editor editor;


    final ArrayList<IndentStatusModel> approvedlist = new ArrayList<IndentStatusModel>();
    final ArrayList<IndentStatusModel> pendinglist = new ArrayList<IndentStatusModel>();
    final ArrayList<IndentStatusModel> regectedlist = new ArrayList<IndentStatusModel>();
    final ArrayList<IndentStatusModel> inprogresslist = new ArrayList<IndentStatusModel>();
    final ArrayList<IndentStatusModel> Issued = new ArrayList<IndentStatusModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_status);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());


        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String user = sharedPreferences.getString("userid",null);

        userid = sharedPreferences.getString("userid",null);

        initialize();

        callapi();

    }


    private void initialize()
    {
        fab_add_raise=findViewById(R.id.fab_add_raise);
        fab_add_raise.setOnClickListener(this);
        indent_status_listView=findViewById(R.id.indent_status_listView);
        iv_indent_filter=findViewById(R.id.iv_indent_filter);
        iv_indent_filter.setOnClickListener(this);
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

                Indentstatusrequest indentstatusrequest = new Indentstatusrequest(userid);

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
            case R.id.iv_indent_filter:

                PopupMenu popupMenu = new PopupMenu(IndentStatusActivity.this, iv_indent_filter);

                popupMenu.getMenuInflater().inflate(R.menu.menu_items, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked

                        if(menuItem.getTitle().equals("Pending")){

                            indentfilter(pendinglist,"1");

                        }else if(menuItem.getTitle().equals("Approved")){

                            indentfilter(approvedlist,"2");

                        }else if(menuItem.getTitle().equals("Rejected")){

                            indentfilter(regectedlist, "3");

                        }else if(menuItem.getTitle().equals("InProgress")){

                            indentfilter(inprogresslist, "4");

                        }else if(menuItem.getTitle().equals("All")){

                            indentfilter(arrayList, "5");

                        }else if(menuItem.getTitle().equals("Issued"))
                        {
                            indentfilter(Issued,"6");
                        }

                        Toast.makeText(IndentStatusActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();

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
                        arrayList.clear();
                        pendinglist.clear();
                        approvedlist.clear();
                        regectedlist.clear();
                        inprogresslist.clear();
                        Issued.clear();

                        for (int i = 0; i<list.size(); i++){

                            arrayList.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));


                            if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Pending")){

                                pendinglist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));
                            }
                            else if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Approved")){

                                approvedlist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));
                            }else  if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Rejected")){

                                regectedlist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));
                            }else  if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("InProgress")){

                                inprogresslist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));
                            }
                            else if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Issued"))
                            {
                                Issued.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),indentStatuslist.getBoq_indent().get(i).getStatus()));
                            }
                        }

                        indentfilter(pendinglist,"0");

                 /*       IndentStatusAdapter numbersArrayAdapter = new IndentStatusAdapter(this, arrayList);
                        ListView indentStatusList = findViewById(R.id.indent_status_listView);
                        indentStatusList.setAdapter(numbersArrayAdapter);*/

                    }else {

                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }


                break;

        }

        }

    private void indentfilter(ArrayList<IndentStatusModel> list, String status) {


        IndentStatusAdapter numbersArrayAdapter = new IndentStatusAdapter(this, list);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView indentlist = findViewById(R.id.indent_status_listView);

        // set the numbersViewAdapter for ListView
        indentlist.setAdapter(numbersArrayAdapter);


    }
}