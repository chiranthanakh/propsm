package com.proteam.projectstoremanagement.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Adapters.MaterialDetailsAdapter;
import com.proteam.projectstoremanagement.Adapters.PendingIndentAdapter;
import com.proteam.projectstoremanagement.Model.MaterialModel;
import com.proteam.projectstoremanagement.Model.PendingIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Indentpendingrequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Response.Indentpending;
import com.proteam.projectstoremanagement.Response.PsmDataStatusHome;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {


    ImageView ivnav,material_delete;
    DrawerLayout drawer_layout;
    TextView tv_raise_boq_indent,tv_individual_indent,tv_pending_indent,tv_consumption,tv_consumption_list,
            indent_status_Count_pending,indent_status_Count_approve,indent_status_Count_rejected,
            indent_status_Count_close;

    private List<MaterialModel> materialdetails = new ArrayList<>();
    private RecyclerView rv_material;
    private MaterialDetailsAdapter materialDetailsAdapter;

    PieChartView pieChartView;

    List<SliceValue> pieData = new ArrayList<>();
    Button btn_raise_indent, btn_consumption;
    ProgressDialog progressDialog;
    Context context = this;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initilize();

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, getColor(R.color.red_light)));
        pieData.add(new SliceValue(25, getColor(R.color.orange_light)));
        pieData.add(new SliceValue(60, getColor(R.color.green_light)));
        pieData.add(new SliceValue(10, getColor(R.color.grey_new)));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(11);
       // pieChartData.setHasCenterCircle(true).setCenterText1("Project Store Management\nStatus").setCenterText1FontSize(9).setCenterText1Color(Color.parseColor("#FF000000"));
        pieChartView.setPieChartData(pieChartData);


        materialDetailsAdapter = new MaterialDetailsAdapter(materialdetails);
        RecyclerView.LayoutManager nlayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_material.setLayoutManager(nlayoutManager);
        rv_material.setItemAnimator(new DefaultItemAnimator());
        rv_material.setAdapter(materialDetailsAdapter);
        prepareNoticeData();



    }
    private void initilize()
    {
        drawer_layout=findViewById(R.id.drawer_layout);
        ivnav=findViewById(R.id.iv_nav_view);
        ivnav.setOnClickListener(this);
        rv_material = (RecyclerView) findViewById(R.id.rv_material);
        pieChartView = findViewById(R.id.chart);
        btn_raise_indent = findViewById(R.id.btn_raise_indent);
        btn_raise_indent.setOnClickListener(this);
        material_delete=findViewById(R.id.material_delete);



        btn_consumption=findViewById(R.id.btn_consumption);
        btn_consumption.setOnClickListener(this);
        tv_raise_boq_indent=findViewById(R.id.tv_raise_boq_indent);
        tv_individual_indent=findViewById(R.id.tv_individual_indent);
        tv_pending_indent=findViewById(R.id.tv_pending_indent);
        tv_consumption=findViewById(R.id.tv_consumption);
        tv_consumption_list=findViewById(R.id.tv_consumption_list);


        indent_status_Count_pending=findViewById(R.id.indent_status_Count_pending);
        indent_status_Count_approve=findViewById(R.id.indent_status_Count_approve);
        indent_status_Count_rejected=findViewById(R.id.indent_status_Count_rejected);
        indent_status_Count_close=findViewById(R.id.indent_status_Count_close);

        tv_raise_boq_indent.setOnClickListener(this);
        tv_individual_indent.setOnClickListener(this);
        tv_pending_indent.setOnClickListener(this);
        tv_consumption.setOnClickListener(this);
        tv_consumption_list.setOnClickListener(this);
        callboqupdateapi();
    }

    private  void prepareNoticeData()
    {
        MaterialModel  materialDetails = new MaterialModel("22", "Blue", "0 ");
        materialdetails.add(materialDetails);

        materialDetails = new MaterialModel("1232", "Mat", "0 ");
        materialdetails.add(materialDetails);
        materialDetails = new MaterialModel("300yuf1", "V-Bracket(ISMC 75x40x6)", "0 ");
        materialdetails.add(materialDetails);
        materialDetails = new MaterialModel("30sy02", "Top Adopter", "376 ");

        materialdetails.add(materialDetails);
        materialDetails = new MaterialModel("3003", "Flat(65x6)(1No)", "0 ");
        materialdetails.add(materialDetails);




    }

    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(MainActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();



                PsmDataRequest psmDataRequest = new PsmDataRequest("71");
                WebServices<PsmDataStatusHome> webServices = new WebServices<PsmDataStatusHome>(MainActivity.this);
                webServices.psmdatahome(WebServices.ApiType.psmdata, psmDataRequest);
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_consumption:
                Intent intentconhome = new Intent(MainActivity.this, CreateConsumptionActivity.class);
                startActivity(intentconhome);
                break;
            case R.id.iv_nav_view:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
            case R.id.back_toolbar:

                break;
            case R.id.btn_raise_indent:
                Intent intentRaise = new Intent(MainActivity.this, IndentStatusActivity.class);
                startActivity(intentRaise);
                break;
            case R.id.tv_raise_boq_indent:
                Intent intentraiseindent = new Intent(MainActivity.this,IndentStatusActivity.class);
                startActivity(intentraiseindent);
                break;
            case R.id.tv_pending_indent:
                Intent intentpending = new Intent(MainActivity.this,PendingIndentListActivity.class);
                startActivity(intentpending);
                break;
            case R.id.tv_individual_indent:
                Intent intentindividual = new Intent(MainActivity.this,IndividualIndentActivity.class);
                startActivity(intentindividual);
                break;
            case R.id.tv_consumption:
                Intent intentcon = new Intent(MainActivity.this,CreateConsumptionActivity.class);
                startActivity(intentcon);
                break;
          /* case R.id.material_delete:
                openDialog();
                break;*/

        }
    }
    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete?");
        builder.setMessage("Are you sure u want to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL) {

            case psmdata:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        PsmDataStatusHome psmDataStatusHome = (PsmDataStatusHome) response;

                        indent_status_Count_pending.setText(psmDataStatusHome.getPending());
                        indent_status_Count_approve.setText(psmDataStatusHome.getApproved());
                        indent_status_Count_rejected.setText(psmDataStatusHome.getRejected());
                        indent_status_Count_close.setText(psmDataStatusHome.getClose());
                    }
                    else
                    {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }

                } else
                {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }
}