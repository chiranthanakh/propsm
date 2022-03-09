package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
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
    ImageView mToolbar,iv_individual_indent_filter;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_consumption;
    private SwipeRefreshLayout sc_Individual_indent_list;
    FloatingActionButton fab_add_individual_indent;
    LinearLayout ll_no_data_individualIndent;
    ListView lv_individual_indent_status;
    ProgressDialog progressDialog;
    String userid;
    SharedPreferences.Editor editor;

    final ArrayList<IndividualIndentListModel> arrayList = new ArrayList<IndividualIndentListModel>();

    final ArrayList<IndividualIndentListModel> approvedlist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> pendinglist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> regectedlist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> inprogresslist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> Issued = new ArrayList<IndividualIndentListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent_list);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String user = sharedPreferences.getString("userid",null);

        userid = sharedPreferences.getString("userid",null);

        initialize();

        callapi();
        sc_Individual_indent_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        ll_no_data_individualIndent=findViewById(R.id.ll_no_data_individualIndent);
        sc_Individual_indent_list = (SwipeRefreshLayout) findViewById(R.id.sc_Individual_indent_list);
        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);

        fab_add_individual_indent=findViewById(R.id.fab_add_individual_indent);
        fab_add_individual_indent.setOnClickListener(this);
        lv_individual_indent_status=findViewById(R.id.lv_individual_indent_status);
        iv_individual_indent_filter=findViewById(R.id.iv_individual_indent_filter);
        iv_individual_indent_filter.setOnClickListener(this);
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

                Indentstatusrequest indentstatusrequest = new Indentstatusrequest(userid);

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
            case R.id.nav_home:
                Intent homeintent = new Intent(IndividualIndentListActivity.this,MainActivity.class);
                startActivity(homeintent);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent bointent = new Intent(IndividualIndentListActivity.this,IndentStatusActivity.class);
                startActivity(bointent);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent cointent = new Intent(IndividualIndentListActivity.this,ConsumptionListActivity.class);
                startActivity(cointent);
                finishAffinity();
                break;

            case R.id.fab_add_individual_indent:
                Intent intent = new Intent(IndividualIndentListActivity.this,IndividualIndentActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_individual_indent_filter:

                PopupMenu popupMenu = new PopupMenu(IndividualIndentListActivity.this, iv_individual_indent_filter);

                popupMenu.getMenuInflater().inflate(R.menu.menu_items, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked

                        if(menuItem.getTitle().equals("Pending")){

                            individualindentfilter(pendinglist,"1");

                        }else if(menuItem.getTitle().equals("Approved")){

                            individualindentfilter(approvedlist,"2");

                        }else if(menuItem.getTitle().equals("Rejected")){

                            individualindentfilter(regectedlist, "3");

                        }else if(menuItem.getTitle().equals("InProgress")){

                            individualindentfilter(inprogresslist, "4");

                        }else if(menuItem.getTitle().equals("All")){

                            individualindentfilter(arrayList, "5");

                        }
                        else if(menuItem.getTitle().equals("Issued"))
                        {
                            individualindentfilter(arrayList,"6");
                        }

                        Toast.makeText(IndividualIndentListActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
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

                        arrayList.clear();
                        pendinglist.clear();
                        approvedlist.clear();
                        regectedlist.clear();
                        inprogresslist.clear();
                        Issued.clear();
                        for (int i = 0; i < list.size(); i++) {

                            arrayList.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(), indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(), indentStatusdirectlist.getDirect_indent().get(i).getStatus()));

                            if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().contains("Pending"))
                            {
                                pendinglist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().contains("Approved"))
                            {
                                approvedlist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().contains("Rejected"))
                            {
                                regectedlist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().contains("InProgress"))
                            {
                                inprogresslist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().contains("Issued"))
                            {
                                Issued.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                        }
                        individualindentfilter(pendinglist,"0");
                     /*   IndividualIndentListAdapter numbersArrayAdapter = new IndividualIndentListAdapter(this, arrayList);
                        ListView indentStatusList = findViewById(R.id.lv_individual_indent_status);
                        indentStatusList.setAdapter(numbersArrayAdapter);*/

                    } else {

                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }


        }
    }

    private void individualindentfilter(ArrayList<IndividualIndentListModel> list, String status) {


        if(list.size()==0){
            ll_no_data_individualIndent.setVisibility(View.VISIBLE);
        }else {

            IndividualIndentListAdapter numbersArrayAdapter = new IndividualIndentListAdapter(this, list);

            // create the instance of the ListView to set the numbersViewAdapter
            ListView individualindentlist = findViewById(R.id.lv_individual_indent_status);

            // set the numbersViewAdapter for ListView
            individualindentlist.setAdapter(numbersArrayAdapter);
        }


    }
}