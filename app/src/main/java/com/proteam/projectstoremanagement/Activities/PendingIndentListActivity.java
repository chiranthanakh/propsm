package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.PendingIndentListAdapter;
import com.proteam.projectstoremanagement.Model.PendingIndentListModel;
import com.proteam.projectstoremanagement.Response.PendingIndentList;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PendingIndentListActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar, filter;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    LinearLayout ll_status_click,ll_no_data_pendingIndent;
    ListView lv_pending_indent_list;
    private SwipeRefreshLayout sc_indent_list_status;
    List pendingindentlist = new ArrayList();

    final ArrayList<PendingIndentListModel> arrayList = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> approvedlist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> pendinglist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> regectedlist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> inprogresslist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> Issued = new ArrayList<PendingIndentListModel>();
    String laststate;
    ProgressDialog progressDialog;

    String userid;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    SharedPreferences.Editor editor1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_indent_list);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String user = sharedPreferences.getString("userid",null);

        userid = sharedPreferences.getString("userid",null);

        prefs = getSharedPreferences("lastState", Context.MODE_PRIVATE);
        editor1 = prefs.edit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initilize();
        callpendingindentapi();
    }

    private void initilize()
    {
        ll_no_data_pendingIndent=findViewById(R.id.ll_no_data_pendingIndent);
        sc_indent_list_status = (SwipeRefreshLayout) findViewById(R.id.sc_indent_list_status1);

        lv_pending_indent_list=findViewById(R.id.lv_pending_indent_list);
        filter = findViewById(R.id.iv_filter);
        filter.setOnClickListener(this);
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setVisibility(View.GONE);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setVisibility(View.GONE);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        nav_consumption.setVisibility(View.GONE);

        sc_indent_list_status.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                startActivity(getIntent());


                /* laststate = prefs.getString("lastState",null);

                if(!laststate.isEmpty()) {
                }else {

                    if(laststate.equals("Pending")){

                        adaptormoves(pendinglist,"1");

                    }else if(laststate.equals("Approved")){

                        adaptormoves(approvedlist,"2");

                    }else if(laststate.equals("Rejected")){

                        adaptormoves(regectedlist, "3");

                    }else if(laststate.equals("InProgress")){

                        adaptormoves(inprogresslist, "4");

                    }else if(laststate.equals("All")){

                        adaptormoves(arrayList, "5");

                    }else if(laststate.equals("Issued"))
                    {
                        adaptormoves(Issued,"6");
                    }
                }*/

                // Your code here
                Toast.makeText(getApplicationContext(), "Refreshing!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                    }
                }, 3000); // Delay in millis
            }
        });
    }

    private void callpendingindentapi() {
        progressDialog=new ProgressDialog(PendingIndentListActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                PendingIndentRequest pendingIndentRequest = new PendingIndentRequest(userid);
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
            case R.id.nav_home:
                Intent Intenthome = new Intent(PendingIndentListActivity.this,MainActivity.class);
                startActivity(Intenthome);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent Intentboq = new Intent(PendingIndentListActivity.this,IndentStatusActivity.class);
                startActivity(Intentboq);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent Intentindi = new Intent(PendingIndentListActivity.this,IndividualIndentListActivity.class);
                startActivity(Intentindi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent Intentcon = new Intent(PendingIndentListActivity.this,ConsumptionListActivity.class);
                startActivity(Intentcon);
                finishAffinity();
                break;

        /*    case R.id.ll_status_click:
                Intent intent = new Intent(PendingIndentListActivity.this,PendingIndentActivity.class);
                startActivity(intent);
                break;*/

            case R.id.iv_filter:

                PopupMenu popupMenu = new PopupMenu(PendingIndentListActivity.this, filter);

                popupMenu.getMenuInflater().inflate(R.menu.menu_items, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked

                        if(menuItem.getTitle().equals("Pending")){

                            adaptormoves(pendinglist,"1");

                        }else if(menuItem.getTitle().equals("Approved")){

                            adaptormoves(approvedlist,"2");

                        }else if(menuItem.getTitle().equals("Rejected")){

                            adaptormoves(regectedlist, "3");

                        }else if(menuItem.getTitle().equals("InProgress")){

                            adaptormoves(inprogresslist, "4");

                        }else if(menuItem.getTitle().equals("All")){

                            adaptormoves(arrayList, "5");

                        }else if(menuItem.getTitle().equals("Issued"))
                        {
                            adaptormoves(Issued,"6");
                        }


                        editor1.putString("liststate", String.valueOf(menuItem.getTitle()));
                        editor1.commit();


                        Toast.makeText(PendingIndentListActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
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
                        Collections.reverse(pendingindentlist);

                        arrayList.clear();
                        pendinglist.clear();
                        approvedlist.clear();
                        regectedlist.clear();
                        inprogresslist.clear();
                        Issued.clear();
                        if(pendingindentlist.size()!=0){

                            for (int i=0;i<pendingindentlist.size();i++){


                                arrayList.add(new PendingIndentListModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus(),pendingIndentModel.getIndent_list().get(i).getIndent_id()));

                                if(pendingIndentModel.getIndent_list().get(i).getStatus().equalsIgnoreCase("Pending")){

                                    pendinglist.add(new PendingIndentListModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus(),pendingIndentModel.getIndent_list().get(i).getIndent_id()));
                                }else if(pendingIndentModel.getIndent_list().get(i).getStatus().equalsIgnoreCase("Approved")){

                                    approvedlist.add(new PendingIndentListModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus(),pendingIndentModel.getIndent_list().get(i).getIndent_id()));
                                }else  if(pendingIndentModel.getIndent_list().get(i).getStatus().equalsIgnoreCase("Rejected")){

                                    regectedlist.add(new PendingIndentListModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus(),pendingIndentModel.getIndent_list().get(i).getIndent_id()));
                                }else  if(pendingIndentModel.getIndent_list().get(i).getStatus().equalsIgnoreCase("InProgress")){

                                    inprogresslist.add(new PendingIndentListModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus(),pendingIndentModel.getIndent_list().get(i).getIndent_id()));
                                }
                                else if(pendingIndentModel.getIndent_list().get(i).getStatus().equalsIgnoreCase("Issued"))
                                {
                                    Issued.add(new PendingIndentListModel(pendingIndentModel.getIndent_list().get(i).getIndent_auto_gen_id(),pendingIndentModel.getIndent_list().get(i).getContractor_name(),pendingIndentModel.getIndent_list().get(i).getStatus(),pendingIndentModel.getIndent_list().get(i).getIndent_id()));
                                }
                            }
                            adaptormoves(pendinglist,"0");
                        } else{
                            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                        }

                        //tv_raise_indent_total_item.setText(String.valueOf(boqcomponentslist.size()));

                        // Now create the instance of the NumebrsViewAdapter and pass
                        // the context and arrayList created above

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



    private void adaptormoves(ArrayList<PendingIndentListModel> list, String status) {

        if(list.size()==0){
            ll_no_data_pendingIndent.setVisibility(View.VISIBLE);
        }else {
            ll_no_data_pendingIndent.setVisibility(View.GONE);
        }

            PendingIndentListAdapter numbersArrayAdapter = new PendingIndentListAdapter(this, list, status);
            // create the instance of the ListView to set the numbersViewAdapter
            ListView pendingindentlist = findViewById(R.id.lv_pending_indent_list);
            // set the numbersViewAdapter for ListView
            pendingindentlist.setAdapter(numbersArrayAdapter);


    }
}