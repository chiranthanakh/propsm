package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
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
    ImageView mToolbar,iv_individual_indent_filter;

    FloatingActionButton fab_add_individual_indent;
    ListView lv_individual_indent_status;
    ProgressDialog progressDialog;
    String userid;
    SharedPreferences.Editor editor;

    final ArrayList<IndividualIndentListModel> arrayList = new ArrayList<IndividualIndentListModel>();

    final ArrayList<IndividualIndentListModel> approvedlist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> pendinglist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> regectedlist = new ArrayList<IndividualIndentListModel>();
    final ArrayList<IndividualIndentListModel> inprogresslist = new ArrayList<IndividualIndentListModel>();
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

    }

    private void initialize()
    {
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

                            individualindentfilter(arrayList, "4");

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

                        for (int i = 0; i < list.size(); i++) {

                            arrayList.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(), indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(), indentStatusdirectlist.getDirect_indent().get(i).getStatus()));

                            if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().equalsIgnoreCase("Pending"))
                            {
                                pendinglist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().equalsIgnoreCase("Approved"))
                            {
                                approvedlist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().equalsIgnoreCase("Rejected"))
                            {
                                regectedlist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
                            }
                            else if(indentStatusdirectlist.getDirect_indent().get(i).getStatus().equalsIgnoreCase("InProgress"))
                            {
                                inprogresslist.add(new IndividualIndentListModel(indentStatusdirectlist.getDirect_indent().get(i).getIndent_auto_gen_id(),indentStatusdirectlist.getDirect_indent().get(i).getContractor_name(),indentStatusdirectlist.getDirect_indent().get(i).getStatus()));
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


        IndividualIndentListAdapter numbersArrayAdapter = new IndividualIndentListAdapter(this, list);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView individualindentlist = findViewById(R.id.lv_individual_indent_status);

        // set the numbersViewAdapter for ListView
        individualindentlist.setAdapter(numbersArrayAdapter);


    }
}