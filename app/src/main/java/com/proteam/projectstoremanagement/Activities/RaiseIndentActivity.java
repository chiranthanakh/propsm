package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Adapters.RaiseIndentAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.RaiseIndentModel;
import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Boqrequest;
import com.proteam.projectstoremanagement.Response.Boqlist;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class RaiseIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    AppCompatButton btn_indent_preview;

    ListView lv_raise_indent_list;
    List boqcomponentslist = new ArrayList();
    final ArrayList<RaiseIndentModel> arrayList = new ArrayList<RaiseIndentModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        initialize();

    }

    private void initialize()
    {
        btn_indent_preview=findViewById(R.id.btn_indent_preview);
        btn_indent_preview.setOnClickListener(this);
        lv_raise_indent_list=findViewById(R.id.lv_raise_indent_list);
        callboqupdateapi();
    }

    private void callboqupdateapi() {

        Boqrequest boqrequest = new Boqrequest("10","2","2");
        WebServices<Boqlist> webServices = new WebServices<Boqlist>(RaiseIndentActivity.this);
        webServices.boqapi( WebServices.ApiType.boq,boqrequest );

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_indent_preview:
                Intent intent = new Intent(RaiseIndentActivity.this,UpateIndentActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {

        switch (URL) {

            case boq:

                if (isSucces) {

                    if(response!=null) {

                        Boqlist boqlist = (Boqlist) response;
                        boqcomponentslist = boqlist.getBoq_list();

                        for (int i=0;i<boqcomponentslist.size();i++){

                            arrayList.add(new RaiseIndentModel(boqlist.getBoq_list().get(i).getMaterial_manual_id(),boqlist.getBoq_list().get(i).getMaterial_name(),boqlist.getBoq_list().get(i).getBalance_boq(),boqlist.getBoq_list().get(i).getQty()));

                        }

                        // Now create the instance of the NumebrsViewAdapter and pass
                        // the context and arrayList created above
                        RaiseIndentAdapter numbersArrayAdapter = new RaiseIndentAdapter(this, arrayList);

                        // create the instance of the ListView to set the numbersViewAdapter
                        ListView raiseindentlistdata = findViewById(R.id.lv_raise_indent_list);

                        // set the numbersViewAdapter for ListView
                        raiseindentlistdata.setAdapter(numbersArrayAdapter);

                    }

                }


                break;


        }


    }
}