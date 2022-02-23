package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Response.StockMaterialNameResponse;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.util.ArrayList;
import java.util.List;

public class AddMaterialStockActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    AppCompatButton btn_add_material;
    Spinner sp_material_nameStock;
    ProgressDialog progressDialog;
    String id;

    List stockmaterialnamelist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material_stock);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());
        initilize();

        sp_material_nameStock.setOnItemSelectedListener(OnCatSpinnerCL);
    }

    private void initilize()
    {
        btn_add_material=findViewById(R.id.btn_add_material);
        btn_add_material.setOnClickListener(this);
        sp_material_nameStock=findViewById(R.id.sp_material_nameStock);
        callboqupdateapi();
    }

    private void callboqupdateapi() {

        progressDialog=new ProgressDialog(AddMaterialStockActivity.this);

        if(progressDialog!=null) {
            if (!progressDialog.isShowing()) {
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();




                WebServices<StockMaterialNameResponse> webServices = new WebServices<StockMaterialNameResponse>(AddMaterialStockActivity.this);
                webServices.stockmaterialname(WebServices.ApiType.materialstockname);
            }
        }

    }




    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL)
        {
            case materialstockname:

                if(progressDialog!=null)
                {
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }
                if (response != null) {

                    List list = new ArrayList();
                    StockMaterialNameResponse stockMaterialNameResponse = (StockMaterialNameResponse) response;

                    list = stockMaterialNameResponse.getMaterial_list();

                    for(int i = 0; i<list.size(); i++ ){

                        stockmaterialnamelist.add(stockMaterialNameResponse.getMaterial_list().get(i).getMaterial_name());
                 }



                    ArrayAdapter adapter=new ArrayAdapter(AddMaterialStockActivity.this,android.R.layout.simple_list_item_1,stockmaterialnamelist);
                    sp_material_nameStock.setAdapter(adapter);


                }else {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                }
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_add_material:
                if (sp_material_nameStock.getSelectedItem() == null) {
                    Toast.makeText(this, "Select Material Name", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
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