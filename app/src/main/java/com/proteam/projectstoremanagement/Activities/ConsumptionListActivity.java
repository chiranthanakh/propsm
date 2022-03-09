package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proteam.projectstoremanagement.Adapters.ConsumptionListAdapter;
import com.proteam.projectstoremanagement.Model.ConsumptionListModel;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.ConsumptionListRequest;
import com.proteam.projectstoremanagement.Request.Indentstatusrequest;
import com.proteam.projectstoremanagement.Response.ConsumptionListResponse;
import com.proteam.projectstoremanagement.Response.IndentStatuslist;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionListActivity extends AppCompatActivity implements View.OnClickListener , OnResponseListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent;
    private SwipeRefreshLayout sc_consumption_list_status;
    ListView consumption_status_listView;
    ProgressDialog progressDialog;
    final ArrayList<ConsumptionListModel> arrayList = new ArrayList<ConsumptionListModel>();
    List list;
    LinearLayout ll_no_data;
    FloatingActionButton fab_add_consumption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_list);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initialize();
        callConsumptionListAPI();
        sc_consumption_list_status.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

        sc_consumption_list_status = (SwipeRefreshLayout) findViewById(R.id.sc_consumption_list_status);
        consumption_status_listView=findViewById(R.id.consumption_status_listView);
        fab_add_consumption=findViewById(R.id.fab_add_consumption);
        fab_add_consumption.setOnClickListener(this);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        ll_no_data = findViewById(R.id.ll_no_data);

    }
    private void callConsumptionListAPI() {

        progressDialog=new ProgressDialog(ConsumptionListActivity.this);
        if(progressDialog!=null)
        {
            if(!progressDialog.isShowing())
            {

                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ConsumptionListRequest consumptionListRequest = new ConsumptionListRequest("1");

                WebServices<ConsumptionListResponse> webServices = new WebServices<ConsumptionListResponse>(ConsumptionListActivity.this);
                webServices.ConsumptionList(WebServices.ApiType.ConsList,consumptionListRequest );
            }
            else {

            }
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code)
    {

        switch (URL) {
            case ConsList:

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
                        ConsumptionListResponse consumptionListResponse = (ConsumptionListResponse) response;

                        list = consumptionListResponse.getClient_consumption_list();
                        arrayList.clear();


                        for (int i = 0; i<list.size(); i++){

                            arrayList.add(new ConsumptionListModel(consumptionListResponse.getClient_consumption_list().get(i).getContractor_name(),consumptionListResponse.getClient_consumption_list().get(i).getLocation_name(),consumptionListResponse.getClient_consumption_list().get(i).getDate(),consumptionListResponse.getClient_consumption_list().get(i).getSite_consumption_id()));

                        }

                        if(arrayList.size()==0){
                            ll_no_data.setVisibility(View.VISIBLE);
                        }else {
                            ConsumptionListAdapter numbersArrayAdapter = new ConsumptionListAdapter(this, arrayList);
                            ListView consumptionList = findViewById(R.id.consumption_status_listView);
                            consumptionList.setAdapter(numbersArrayAdapter);
                        }


                    }else {

                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();

                }


                break;

        }

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.fab_add_consumption:
                Intent intent_conList = new Intent(ConsumptionListActivity.this,CreateConsumptionActivity.class);
                startActivity(intent_conList);
                break;
            case R.id.nav_home:
                Intent intent_home= new Intent(ConsumptionListActivity.this,MainActivity.class);
                startActivity(intent_home);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent intent_boq = new Intent(ConsumptionListActivity.this,IndentStatusActivity.class);
                startActivity(intent_boq);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent intent_individual = new Intent(ConsumptionListActivity.this,IndividualIndentListActivity.class);
                startActivity(intent_individual);
                finishAffinity();
                break;
        }
    }
}