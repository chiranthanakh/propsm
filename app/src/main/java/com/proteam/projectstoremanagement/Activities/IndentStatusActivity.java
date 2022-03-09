package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    private SwipeRefreshLayout sc_indent_list_status;
    BottomNavigationItemView nav_home,nav_Individual_indent,nav_consumption;

    FloatingActionButton fab_add_raise;
    ListView indent_status_listView;
    ProgressDialog progressDialog;
    LinearLayout ll_no_data_indentstatus;
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

        sc_indent_list_status.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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


    private void initialize()
    {
        ll_no_data_indentstatus =findViewById(R.id.ll_no_data_indentstatus);
        sc_indent_list_status = (SwipeRefreshLayout) findViewById(R.id.sc_indent_list_status);

        fab_add_raise=findViewById(R.id.fab_add_raise);
        fab_add_raise.setOnClickListener(this);
        indent_status_listView=findViewById(R.id.indent_status_listView);
        iv_indent_filter=findViewById(R.id.iv_indent_filter);
        iv_indent_filter.setOnClickListener(this);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
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
            case R.id.nav_consumption:
                Intent intentConList = new Intent(IndentStatusActivity.this,ConsumptionListActivity.class);
                startActivity(intentConList);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent intentIndividual = new Intent(IndentStatusActivity.this,IndividualIndentListActivity.class);
                startActivity(intentIndividual);
                finishAffinity();
                break;
            case R.id.nav_home:
                Intent intentHome = new Intent(IndentStatusActivity.this,MainActivity.class);
                startActivity(intentHome);
                finishAffinity();
                break;
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

                            arrayList.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),
                                    indentStatuslist.getBoq_indent().get(i).getStatus(),indentStatuslist.getBoq_indent().get(i).getIndent_id(),
                                    indentStatuslist.getBoq_indent().get(i).getLocation_name(),indentStatuslist.getBoq_indent().get(i).getSub_location_name(),
                                    indentStatuslist.getBoq_indent().get(i).getContractor(),indentStatuslist.getBoq_indent().get(i).getBlock(),
                                    indentStatuslist.getBoq_indent().get(i).getLocations(),indentStatuslist.getBoq_indent().get(i).getIndent_date(),
                                    indentStatuslist.getBoq_indent().get(i).getStore_id()
                                    ));


                            if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Pending")){

                                pendinglist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),
                                        indentStatuslist.getBoq_indent().get(i).getStatus(),indentStatuslist.getBoq_indent().get(i).getIndent_id(),
                                        indentStatuslist.getBoq_indent().get(i).getLocation_name(),indentStatuslist.getBoq_indent().get(i).getSub_location_name(),
                                        indentStatuslist.getBoq_indent().get(i).getContractor(),indentStatuslist.getBoq_indent().get(i).getBlock(),
                                        indentStatuslist.getBoq_indent().get(i).getLocations(),indentStatuslist.getBoq_indent().get(i).getIndent_date(),
                                        indentStatuslist.getBoq_indent().get(i).getStore_id()
                                ));       }
                            else if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Approved")){

                                approvedlist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),
                                        indentStatuslist.getBoq_indent().get(i).getStatus(),indentStatuslist.getBoq_indent().get(i).getIndent_id(),
                                        indentStatuslist.getBoq_indent().get(i).getLocation_name(),indentStatuslist.getBoq_indent().get(i).getSub_location_name(),
                                        indentStatuslist.getBoq_indent().get(i).getContractor(),indentStatuslist.getBoq_indent().get(i).getBlock(),
                                        indentStatuslist.getBoq_indent().get(i).getLocations(),indentStatuslist.getBoq_indent().get(i).getIndent_date(),
                                        indentStatuslist.getBoq_indent().get(i).getStore_id()
                                ));

                            }else  if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Rejected")){

                                regectedlist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),
                                        indentStatuslist.getBoq_indent().get(i).getStatus(),indentStatuslist.getBoq_indent().get(i).getIndent_id(),
                                        indentStatuslist.getBoq_indent().get(i).getLocation_name(),indentStatuslist.getBoq_indent().get(i).getSub_location_name(),
                                        indentStatuslist.getBoq_indent().get(i).getContractor(),indentStatuslist.getBoq_indent().get(i).getBlock(),
                                        indentStatuslist.getBoq_indent().get(i).getLocations(),indentStatuslist.getBoq_indent().get(i).getIndent_date(),
                                        indentStatuslist.getBoq_indent().get(i).getStore_id()
                                ));

                            }else  if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("InProgress")){

                                inprogresslist.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),
                                        indentStatuslist.getBoq_indent().get(i).getStatus(),indentStatuslist.getBoq_indent().get(i).getIndent_id(),
                                        indentStatuslist.getBoq_indent().get(i).getLocation_name(),indentStatuslist.getBoq_indent().get(i).getSub_location_name(),
                                        indentStatuslist.getBoq_indent().get(i).getContractor(),indentStatuslist.getBoq_indent().get(i).getBlock(),
                                        indentStatuslist.getBoq_indent().get(i).getLocations(),indentStatuslist.getBoq_indent().get(i).getIndent_date(),
                                        indentStatuslist.getBoq_indent().get(i).getStore_id()
                                ));

                            }
                            else if(indentStatuslist.getBoq_indent().get(i).getStatus().contains("Issued"))
                            {
                                Issued.add(new IndentStatusModel(indentStatuslist.getBoq_indent().get(i).getIndent_auto_gen_id(),indentStatuslist.getBoq_indent().get(i).getContractor_name(),
                                        indentStatuslist.getBoq_indent().get(i).getStatus(),indentStatuslist.getBoq_indent().get(i).getIndent_id(),
                                        indentStatuslist.getBoq_indent().get(i).getLocation_name(),indentStatuslist.getBoq_indent().get(i).getSub_location_name(),
                                        indentStatuslist.getBoq_indent().get(i).getContractor(),indentStatuslist.getBoq_indent().get(i).getBlock(),
                                        indentStatuslist.getBoq_indent().get(i).getLocations(),indentStatuslist.getBoq_indent().get(i).getIndent_date(),
                                        indentStatuslist.getBoq_indent().get(i).getStore_id()
                                ));
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



        if(list.size()==0){
            ll_no_data_indentstatus.setVisibility(View.VISIBLE);
        }else {

        IndentStatusAdapter numbersArrayAdapter = new IndentStatusAdapter(this, list);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView indentlist = findViewById(R.id.indent_status_listView);

        // set the numbersViewAdapter for ListView
        indentlist.setAdapter(numbersArrayAdapter);
        }

    }
}