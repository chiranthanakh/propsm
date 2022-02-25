package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Adapters.PendingIndentListAdapter;
import com.proteam.projectstoremanagement.Model.PendingIndentListModel;
import com.proteam.projectstoremanagement.Response.PendingIndentList;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.PendingIndentRequest;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class PendingIndentListActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar, filter;
    LinearLayout ll_status_click;
    ListView lv_pending_indent_list;

    List pendingindentlist = new ArrayList();



    final ArrayList<PendingIndentListModel> arrayList = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> approvedlist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> pendinglist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> regectedlist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> inprogresslist = new ArrayList<PendingIndentListModel>();
    final ArrayList<PendingIndentListModel> Issued = new ArrayList<PendingIndentListModel>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_indent_list);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());



    }

    @Override
    protected void onResume() {
        super.onResume();
        initilize();
        callpendingindentapi();
    }

    private void initilize()
    {
        lv_pending_indent_list=findViewById(R.id.lv_pending_indent_list);
        filter = findViewById(R.id.iv_filter);
        filter.setOnClickListener(this);

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

                        arrayList.clear();
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

        PendingIndentListAdapter numbersArrayAdapter = new PendingIndentListAdapter(this, list,status);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView pendingindentlist = findViewById(R.id.lv_pending_indent_list);

        // set the numbersViewAdapter for ListView
        pendingindentlist.setAdapter(numbersArrayAdapter);


    }
}