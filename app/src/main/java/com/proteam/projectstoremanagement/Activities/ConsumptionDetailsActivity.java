package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IncorrectContextUseViolation;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.ConsumptionDetailsAdapter;
import com.proteam.projectstoremanagement.Adapters.PendingIndentAdapter;
import com.proteam.projectstoremanagement.Model.ConsumptionDetailsModel;
import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.ConsumptionDetailsRequest;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Response.ConsumptionDetailsDataResponse;
import com.proteam.projectstoremanagement.Response.Indentpending;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionDetailsActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {

    ImageView mToolbar;

    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;
    String id;
    TextView tv_site_consumption_id,tv_Consumption_ConName,tv_Consumption_location,tv_Consumption_subLocation,
    tv_consumption_ConsDate,tv_Consumption_ConLastDate,tv_consumption_details_total_item;

    ProgressDialog progressDialog;
    List ConsumptionDe = new ArrayList();
    final ArrayList<ConsumptionDetailsModel> arrayList = new ArrayList<ConsumptionDetailsModel>();

    List ConsID = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_details);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("siteConsumptionID");
        initilze();
        callConsumptionDetailsAPI();
    }

    private void initilze()
    {
        tv_site_consumption_id=findViewById(R.id.tv_site_consumption_id);
        tv_Consumption_ConName=findViewById(R.id.tv_Consumption_ConName);
        tv_Consumption_location=findViewById(R.id.tv_Consumption_location);
        tv_Consumption_subLocation=findViewById(R.id.tv_Consumption_subLocation);
        tv_consumption_ConsDate=findViewById(R.id.tv_consumption_ConsDate);
        tv_Consumption_ConLastDate=findViewById(R.id.tv_Consumption_ConLastDate);
        tv_consumption_details_total_item=findViewById(R.id.tv_consumption_details_total_item);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
    }

    private void callConsumptionDetailsAPI() {

        progressDialog=new ProgressDialog(ConsumptionDetailsActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ConsumptionDetailsRequest consumptionDetailsRequest = new ConsumptionDetailsRequest(id);
                WebServices<ConsumptionDetailsDataResponse> webServices = new WebServices<ConsumptionDetailsDataResponse>(ConsumptionDetailsActivity.this);
                webServices.consumptionDetails(WebServices.ApiType.consumptionDetails, consumptionDetailsRequest);
            }
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code)
    {
        switch (URL)
        {
            case consumptionDetails:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        ConsumptionDetailsDataResponse consumptionDetailsDataResponse = (ConsumptionDetailsDataResponse) response;

                        ConsumptionDe = consumptionDetailsDataResponse.getMaterils();

                        arrayList.clear();
                        for (int i=0;i<ConsumptionDe.size();i++){

                            arrayList.add(new ConsumptionDetailsModel(consumptionDetailsDataResponse.getMaterils().get(i).getMaterial_manual_id(),consumptionDetailsDataResponse.getMaterils().get(i).getMaterial_name(),consumptionDetailsDataResponse.getMaterils().get(i).getConsumption_qty()));

                            ConsID.add(consumptionDetailsDataResponse.getMaterils().get(0).getSite_consumption_id());
                        }
                        tv_consumption_details_total_item.setText(String.valueOf(ConsumptionDe.size()));

                        // the context and arrayList created above
                        ConsumptionDetailsAdapter numbersArrayAdapter = new ConsumptionDetailsAdapter(this, arrayList);

                        // create the instance of the ListView to set the numbersViewAdapter
                        ListView consumptiondetails = findViewById(R.id.lv_consumption_details_list);

                        // set the numbersViewAdapter for ListView
                        consumptiondetails.setAdapter(numbersArrayAdapter);

                        tv_site_consumption_id.setText(consumptionDetailsDataResponse.getContractor_location().get(0).getSite_consumption_id());
                        tv_Consumption_ConName.setText(consumptionDetailsDataResponse.getContractor_location().get(0).getContractor_name());
                        tv_Consumption_location.setText(consumptionDetailsDataResponse.getContractor_location().get(0).getLocation_name());
                        tv_Consumption_subLocation.setText(consumptionDetailsDataResponse.getContractor_location().get(0).getSub_location_name());
                        tv_consumption_ConsDate.setText(consumptionDetailsDataResponse.getContractor_location().get(0).getDate());
                        tv_Consumption_ConLastDate.setText(consumptionDetailsDataResponse.getContractor_location().get(0).getLast_updated_date());

                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.nav_home:
                Intent intent_home = new Intent(ConsumptionDetailsActivity.this,MainActivity.class);
                startActivity(intent_home);
                finishAffinity();
                break;

            case R.id.nav_boq_indent:
                Intent intent_boq= new Intent(ConsumptionDetailsActivity.this,IndentStatusActivity.class);
                startActivity(intent_boq);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent intent_indi = new Intent(ConsumptionDetailsActivity.this,IndividualIndentListActivity.class);
                startActivity(intent_indi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent intent_cons = new Intent(ConsumptionDetailsActivity.this,ConsumptionListActivity.class);
                startActivity(intent_cons);
                finishAffinity();
                break;
        }
    }


}