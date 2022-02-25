package com.proteam.projectstoremanagement.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Adapters.IndividualIndentListAdapter;
import com.proteam.projectstoremanagement.Adapters.MaterialDetailsAdapter;
import com.proteam.projectstoremanagement.Model.IndividualIndentListModel;
import com.proteam.projectstoremanagement.Model.MaterialSModel;
import com.proteam.projectstoremanagement.Model.MaterialStockModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Request.MaterialStockRequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.IndentStatusdirectlist;
import com.proteam.projectstoremanagement.Response.PsmDataStatusHome;
import com.proteam.projectstoremanagement.Utils.OnClick;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener, OnClick {


    ImageView ivnav, iv_add_material;
    DrawerLayout drawer_layout;
    TextView tv_raise_boq_indent, tv_individual_indent, tv_pending_indent, tv_consumption, tv_consumption_list,
            indent_status_Count_pending, indent_status_Count_approve, indent_status_Count_rejected,
            indent_status_Count_close, iv_nav_email, tv_nav_username;

    Button btn_change_pass;

    private List<MaterialSModel> materialdetails = new ArrayList<>();
    private RecyclerView rv_material;
    private MaterialDetailsAdapter materialDetailsAdapter;

    PieChartView pieChartView;

    List<SliceValue> pieData = new ArrayList<>();
    Button btn_raise_indent, btn_consumption, btn_logout;
    ProgressDialog progressDialog;
    Context context = this;
    ListView lv_material_stock_home;
    String role, userid, email, username;
    SharedPreferences.Editor editor;

    final ArrayList<MaterialSModel> arrayList = new ArrayList<MaterialSModel>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String user = sharedPreferences.getString("userid", null);

        if (user == null) {

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        role = sharedPreferences.getString("role", null);
        userid = sharedPreferences.getString("userid", null);
        email = sharedPreferences.getString("email", null);
        username = sharedPreferences.getString("username", null);

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


    }

    private void initilize() {

        /*Bundle bundle = getIntent().getExtras();
        role = bundle.getString("role");
        userid = bundle.getString("user_id");*/
        // role = "Approver";
        drawer_layout = findViewById(R.id.drawer_layout_main);
        ivnav = findViewById(R.id.iv_nav_view);
        ivnav.setOnClickListener(this);
        lv_material_stock_home = findViewById(R.id.lv_material_stock_home);
        pieChartView = findViewById(R.id.chart);
        btn_raise_indent = findViewById(R.id.btn_raise_indent);
        btn_raise_indent.setOnClickListener(this);
        iv_add_material = findViewById(R.id.iv_add_material);
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
        iv_add_material.setOnClickListener(this);
        tv_nav_username = findViewById(R.id.tv_nav_username);
        tv_nav_username.setText(username);

        iv_nav_email = findViewById(R.id.iv_nav_email);
        iv_nav_email.setText(email);
        btn_consumption = findViewById(R.id.btn_consumption);
        btn_consumption.setOnClickListener(this);
        tv_raise_boq_indent = findViewById(R.id.tv_raise_boq_indent);
        tv_individual_indent = findViewById(R.id.tv_individual_indent);
        tv_pending_indent = findViewById(R.id.tv_pending_indent);
        tv_consumption = findViewById(R.id.tv_consumption);
        tv_consumption_list = findViewById(R.id.tv_consumption_list);

        btn_change_pass = findViewById(R.id.btn_change_pass);
        btn_change_pass.setOnClickListener(this);
        indent_status_Count_pending = findViewById(R.id.indent_status_Count_pending);
        indent_status_Count_approve = findViewById(R.id.indent_status_Count_approve);
        indent_status_Count_rejected = findViewById(R.id.indent_status_Count_rejected);
        indent_status_Count_close = findViewById(R.id.indent_status_Count_close);

        if (role.equals("Approver")) {

        } else {
            tv_pending_indent.setVisibility(View.GONE);
        }

        tv_raise_boq_indent.setOnClickListener(this);
        tv_individual_indent.setOnClickListener(this);
        tv_pending_indent.setOnClickListener(this);
        tv_consumption.setOnClickListener(this);
        tv_consumption_list.setOnClickListener(this);
        callboqupdateapi();
        // callmaterialstockapi();


    }


    private void callmaterialstockapi() {

        //progressDialog=new ProgressDialog(MainActivity.this);


        MaterialStockModel materialStockModel = new MaterialStockModel(userid);
        WebServices<MaterialStockRequest> webServices = new WebServices<MaterialStockRequest>(MainActivity.this);
        webServices.materialstocklisthome(WebServices.ApiType.materialstock, materialStockModel);


        /*if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


       }
        }*/

    }

    private void callboqupdateapi() {

        progressDialog = new ProgressDialog(MainActivity.this);

        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                PsmDataRequest psmDataRequest = new PsmDataRequest(userid);
                WebServices<PsmDataStatusHome> webServices = new WebServices<PsmDataStatusHome>(MainActivity.this);
                webServices.psmdatahome(WebServices.ApiType.psmdata, psmDataRequest);
            }
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        callmaterialstockapi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_pass:
                Intent intentChangePass = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intentChangePass);
                break;

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
                Intent intentraiseindent = new Intent(MainActivity.this, IndentStatusActivity.class);
                startActivity(intentraiseindent);
                break;
            case R.id.tv_pending_indent:
                Intent intentpending = new Intent(MainActivity.this, PendingIndentListActivity.class);
                startActivity(intentpending);
                break;
            case R.id.tv_individual_indent:
                Intent intentindividual = new Intent(MainActivity.this, IndividualIndentListActivity.class);
                startActivity(intentindividual);
                break;
            case R.id.tv_consumption:
                Intent intentcon = new Intent(MainActivity.this, CreateConsumptionActivity.class);
                startActivity(intentcon);
                break;
            case R.id.iv_add_material:
                Intent intentaddM = new Intent(MainActivity.this, AddMaterialStockActivity.class);
                startActivity(intentaddM);
                break;


            case R.id.btn_logout:
                editor.clear();
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

          /* case R.id.material_delete:
                openDialog();
                break;*/

        }
    }

    public void openDialog(String de) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage("Are you sure u want to delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                //   onClickitem(de);
                dialog.cancel();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClickitem(String value) {

        progressDialog = new ProgressDialog(MainActivity.this);

        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                MaterialStockDeleteRequest materialStockDeleteRequest = new MaterialStockDeleteRequest(value);
                WebServices<Generalresponce> webServices = new WebServices<Generalresponce>(MainActivity.this);
                webServices.deleteMstockdata(WebServices.ApiType.deletestockMhome, materialStockDeleteRequest);
            }
        }


    }


    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL) {

            case psmdata:
                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if (response != null) {

                        PsmDataStatusHome psmDataStatusHome = (PsmDataStatusHome) response;

                        indent_status_Count_pending.setText(psmDataStatusHome.getPending());
                        indent_status_Count_approve.setText(psmDataStatusHome.getApproved());
                        indent_status_Count_rejected.setText(psmDataStatusHome.getRejected());
                        indent_status_Count_close.setText(psmDataStatusHome.getClose());
                    } else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                break;

            case materialstock:

                if (progressDialog != null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if (response != null) {

                        List list = new ArrayList();
                        MaterialStockRequest materialStockRequest = (MaterialStockRequest) response;

                        list = materialStockRequest.getMaterial_closing_details();
                        arrayList.clear();
                        for (int i = 0; i < list.size(); i++) {

                            arrayList.add(new MaterialSModel(materialStockRequest.getMaterial_closing_details().get(i).getMaterial_manual_id(), materialStockRequest.getMaterial_closing_details().get(i).getMaterial_name(), materialStockRequest.getMaterial_closing_details().get(i).getClosing_stock(), materialStockRequest.getMaterial_closing_details().get(i).getFavorite_id()));
                        }

                        MaterialDetailsAdapter numbersArrayAdapter = new MaterialDetailsAdapter(this, arrayList, this);
                        ListView materialstocklist = findViewById(R.id.lv_material_stock_home);
                        materialstocklist.setAdapter(numbersArrayAdapter);
                        materialstocklist.invalidate();

                    } else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;

            case deletestockMhome:

                if (isSucces) {

                    if (response != null) {


                        Generalresponce generalresponce = (Generalresponce) response;
                        Toast.makeText(this, generalresponce.getStatus(), Toast.LENGTH_SHORT).show();

                        callmaterialstockapi();

                    } else {
                        Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }


                break;


        }
    }

}