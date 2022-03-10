package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.Adapters.IndividualIndentMaterialAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.IndividualIndentMaterialModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.IndividualListrequest;
import com.proteam.projectstoremanagement.Request.Individuallistitems;
import com.proteam.projectstoremanagement.Request.Individualmaterialdetails;
import com.proteam.projectstoremanagement.Request.Raiseintentdataitems;
import com.proteam.projectstoremanagement.Request.SubLocationRaiseRequest;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Response.IndividualMaterialListResponse;
import com.proteam.projectstoremanagement.Response.Individualresponse;
import com.proteam.projectstoremanagement.Response.StockMaterialNameResponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndividualIndentMaterialActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home, nav_boq_indent, nav_Individual_indent, nav_consumption;
    ProgressDialog progressDialog;
    Spinner sp_indi_material;
    ListView lv_individual_in_material;
    AppCompatButton add_meterial;
    EditText edt_qty;
    Button btn_approval,btn_back;
    List materialList = new ArrayList();
    final ArrayList<IndividualIndentMaterialModel> arrayList = new ArrayList<IndividualIndentMaterialModel>();
    Map map = new HashMap();
    String location,sublocation,contrctorname,storeid,location_id,sublocation_id,contrctorname_id,date,workorderno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent_material);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        storeid = sharedPreferences.getString("store_id",null);


        Bundle bundle = getIntent().getExtras();
        location_id = bundle.getString("location_id");
        sublocation_id =bundle.getString("sublocation_id");
        contrctorname_id = bundle.getString("contractor_id");
        location = bundle.getString("location_name");
        sublocation =bundle.getString("sublocation_name");
        contrctorname = bundle.getString("contractor_name");
        date = bundle.getString("date");
        workorderno=bundle.getString("workorderno");


        initilize();

        sp_indi_material.setOnItemSelectedListener(OnCatSpinnerCL);

    }

    private void initilize() {
        sp_indi_material = findViewById(R.id.sp_indi_material);
        lv_individual_in_material = findViewById(R.id.lv_individual_in_material);
        nav_home = findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent = findViewById(R.id.nav_boq_indent);
        add_meterial = findViewById(R.id.add_meterial);
        edt_qty = findViewById(R.id.edt_qty);
        add_meterial.setOnClickListener(this);
        nav_boq_indent.setOnClickListener(this);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        nav_Individual_indent = findViewById(R.id.nav_Individual_indent);
        btn_approval = findViewById(R.id.btn_approval);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption = findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);
        callIndividualMatList();

        add_meterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String material = sp_indi_material.getSelectedItem().toString();
                String id = String.valueOf(map.get(material));

                if(edt_qty.getText().toString().trim().equals("")){

                    Toast.makeText(IndividualIndentMaterialActivity.this, "Please Enter quentity", Toast.LENGTH_SHORT).show();

                }else {

                    arrayList.add(new IndividualIndentMaterialModel(id, material, edt_qty.getText().toString().trim()));

                }

                IndividualIndentMaterialAdapter numbersArrayAdapter = new IndividualIndentMaterialAdapter(IndividualIndentMaterialActivity.this, arrayList);
                ListView indentStatusList = findViewById(R.id.lv_individual_in_material);
                indentStatusList.setAdapter(numbersArrayAdapter);

            }
        });

        btn_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callIndividuaintentapproval();

            }
        });
    }

    private void callIndividualMatList() {

        progressDialog = new ProgressDialog(IndividualIndentMaterialActivity.this);

        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                WebServices<IndividualMaterialListResponse> webServices = new WebServices<IndividualMaterialListResponse>(IndividualIndentMaterialActivity.this);
                webServices.stockmaterialname(WebServices.ApiType.IndividualMatlistName);
            }
        }
    }


    private void callIndividuaintentapproval() {

        progressDialog = new ProgressDialog(IndividualIndentMaterialActivity.this);
        SharedPreferences sharedPreferences=this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String user = sharedPreferences.getString("userid",null);


        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                ArrayList<Individualmaterialdetails> individuallist = new ArrayList<>();

                for(int i=0;i<arrayList.size();i++){
                    individuallist.add(new Individualmaterialdetails(arrayList.get(i).getMaterialCode(),"remarks",arrayList.get(i).getIndentQty()));
                }

                ArrayList<Individuallistitems> contravtoritem = new ArrayList<>();
                contravtoritem.add(new Individuallistitems(contrctorname_id,location_id,sublocation_id,date,user,"remarks"));

                IndividualListrequest individualListrequest = new IndividualListrequest(contravtoritem,individuallist);

                WebServices<IndividualMaterialListResponse> webServices = new WebServices<IndividualMaterialListResponse>(IndividualIndentMaterialActivity.this);
                webServices.individualapprove(WebServices.ApiType.listresponse, individualListrequest);
            }
        }
    }



    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL) {
            case IndividualMatlistName:

                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
                if (isSucces) {
                    if (response != null) {

                        List list = new ArrayList();
                        StockMaterialNameResponse stockMaterialNameResponse = (StockMaterialNameResponse) response;

                        list = stockMaterialNameResponse.getMaterial_list();

                        for (int i = 0; i < list.size(); i++) {

                            materialList.add(stockMaterialNameResponse.getMaterial_list().get(i).getMaterial_name());
                            map.put(stockMaterialNameResponse.getMaterial_list().get(i).getMaterial_name(),stockMaterialNameResponse.getMaterial_list().get(i).getMaterial_id());
                            // map.put(stockMaterialNameResponse.getMaterial_list().get(i).getMaterial_name(),stockMaterialNameResponse.getMaterial_list().get(i).getMaterial_id());
                        }


                        ArrayAdapter adapte = new ArrayAdapter(IndividualIndentMaterialActivity.this, android.R.layout.simple_list_item_1, materialList);
                        sp_indi_material.setAdapter(adapte);


                    } else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                break;

            case listresponse:


                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
                if (isSucces) {
                    if (response != null) {

                        Individualresponse individualresponse = (Individualresponse) response;
                        Toast.makeText(this, individualresponse.getStatus(), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.nav_home:
                Intent IntentHome = new Intent(IndividualIndentMaterialActivity.this, MainActivity.class);
                startActivity(IntentHome);
                // finishAndRemoveTask();
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent Intentboq = new Intent(IndividualIndentMaterialActivity.this, IndentStatusActivity.class);
                startActivity(Intentboq);
                // finishAndRemoveTask();
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent IntentIndi = new Intent(IndividualIndentMaterialActivity.this, IndividualIndentListActivity.class);
                startActivity(IntentIndi);
                //finishAndRemoveTask();
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent Intentcon = new Intent(IndividualIndentMaterialActivity.this, ConsumptionListActivity.class);
                startActivity(Intentcon);
                //finishAndRemoveTask();
                finishAffinity();
                break;

            case R.id.btn_back:
                Intent Intentcon1 = new Intent(IndividualIndentMaterialActivity.this, ConsumptionListActivity.class);
                startActivity(Intentcon1);
                finishAffinity();

                break;
        }
    }

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(14);

        }

        public void onNothingSelected(AdapterView<?> parent) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(14);
        }
    };


}