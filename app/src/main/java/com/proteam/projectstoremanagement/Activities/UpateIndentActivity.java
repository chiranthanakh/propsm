package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Adapters.UpdateIndentAdapter;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Request.MaterialStockDeleteRequest;
import com.proteam.projectstoremanagement.Request.PsmDataRequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Response.Generalresponce;
import com.proteam.projectstoremanagement.Response.PsmDataStatusHome;
import com.proteam.projectstoremanagement.Response.RaiseIndentConfirmRequest;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class UpateIndentActivity extends AppCompatActivity implements OnResponseListener, View.OnClickListener {
    ImageView mToolbar;
    ListView lv_update_indent_list;
    TextView tv_total_item;
    AppCompatButton btn_indent_confirm;

    List boqcomponentslist = new ArrayList();
    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upate_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());



        initilize();


    }

    private void initilize() {

        tv_total_item=findViewById(R.id.tv_update_indent_total_item);
        lv_update_indent_list=findViewById(R.id.lv_update_indent_list);
        btn_indent_confirm=findViewById(R.id.btn_indent_confirm);
        btn_indent_confirm.setOnClickListener(this);
        callboqupdateapi();




    }

    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(UpateIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();


                Boqrequest boqrequest = new Boqrequest("10", "2", "2");
                WebServices<Boqlist> webServices = new WebServices<Boqlist>(UpateIndentActivity.this);
                webServices.boqapi(WebServices.ApiType.boq, boqrequest);
            }
        }

    }


    private void callConfirmApi() {

        progressDialog=new ProgressDialog(UpateIndentActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                RaiseIndentConfirmRequest raiseIndentConfirmRequest = new RaiseIndentConfirmRequest("6");
                WebServices<Generalresponce> webServices = new WebServices<Generalresponce>(UpateIndentActivity.this);
                webServices.confirmRaiseIndent(WebServices.ApiType.confirmRaiseIndent,raiseIndentConfirmRequest);
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_indent_confirm:

                   callConfirmApi();
                break;
        }
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {

            case boq:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {

                        Boqlist boqlist = (Boqlist) response;
                        boqcomponentslist = boqlist.getBoq_list();

                        for (int i=0;i<boqcomponentslist.size();i++){

                            arrayList.add(new RaiseIndentModel(boqlist.getBoq_list().get(i).getMaterial_manual_id(),boqlist.getBoq_list().get(i).getMaterial_name(),boqlist.getBoq_list().get(i).getBalance_boq(),boqlist.getBoq_list().get(i).getQty()));

                        }

                        tv_total_item.setText(String.valueOf(boqcomponentslist.size()));


                        // the context and arrayList created above
                        UpdateIndentAdapter numbersArrayAdapter = new UpdateIndentAdapter(this, arrayList);

                        // create the instance of the ListView to set the numbersViewAdapter
                        ListView updateindentList = findViewById(R.id.lv_update_indent_list);

                        // set the numbersViewAdapter for ListView
                        updateindentList.setAdapter(numbersArrayAdapter);

                    }

                }
                break;

            case confirmRaiseIndent:
                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }

                if (isSucces) {

                    if(response!=null) {


                        Generalresponce generalresponce = (Generalresponce) response;
                        Toast.makeText(this, generalresponce.getStatus(), Toast.LENGTH_SHORT).show();



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