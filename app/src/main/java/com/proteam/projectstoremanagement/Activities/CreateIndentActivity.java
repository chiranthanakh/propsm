package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.proteam.projectstoremanagement.R;
import com.proteam.projectstoremanagement.Request.Constructorlocationrequest;
import com.proteam.projectstoremanagement.Response.Contractorlocationmodel;
import com.proteam.projectstoremanagement.Utils.OnResponseListener;
import com.proteam.projectstoremanagement.WebServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;

public class CreateIndentActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListener {
    ImageView mToolbar;
    int mMonth,mDay,mYear;
    Spinner spinner_contractor_name, spinner_location, spinner_feeder_name;
    EditText edt_indent_date;

    List contractorlist = new ArrayList();
    List location = new ArrayList();

    AppCompatButton btn_indent_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_add);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());


        initilize();

        calllocationapi();


        spinner_contractor_name.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner_location.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner_feeder_name.setOnItemSelectedListener(OnCatSpinnerCL);



    }


    private void initilize() {
        spinner_contractor_name = findViewById(R.id.spinner_contractor_name);
        spinner_location = findViewById(R.id.spinner_location);
        spinner_feeder_name = findViewById(R.id.spinner_feeder_name);
        edt_indent_date = findViewById(R.id.edt_indent_date);
        edt_indent_date.setOnClickListener(this);
        btn_indent_generate=findViewById(R.id.btn_indent_generate);
        btn_indent_generate.setOnClickListener(this);


    }


    private void calllocationapi() {

        Constructorlocationrequest constructorlocationrequest = new Constructorlocationrequest("puma_client@gmail.com",10);

        WebServices<Contractorlocationmodel> webServices = new WebServices<Contractorlocationmodel >(CreateIndentActivity.this);
        webServices.constructorlocation( WebServices.ApiType.location,constructorlocationrequest );

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_indent_generate:
                Intent intent = new Intent(CreateIndentActivity.this,RaiseIndentActivity.class);
                startActivity(intent);
                break;
            case R.id.edt_indent_date:
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(CreateIndentActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                            Calendar calendar =Calendar.getInstance();
                            calendar.set(Calendar.MONTH,monthofyear);
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                            edt_indent_date.setText(simpleDateFormat.format(calendar.getTime()));

                        }
                    },mYear, mMonth, mDay);
            datePickerDialog.show();
            break;
        }
    }


    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);

        }

        public void onNothingSelected(AdapterView<?> parent) {
            ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
            ((TextView) parent.getChildAt(0)).setTextSize(15);
        }
    };


    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces, int code) {
        switch (URL) {
            case location:

                if (response != null) {

                    List list = new ArrayList();
                    List list2 = new ArrayList();
                    Contractorlocationmodel contractorlocationmodel = (Contractorlocationmodel) response;

                        list = contractorlocationmodel.getLocations();

                    for(int i = 0; i<list.size(); i++ ){

                        location.add(contractorlocationmodel.getLocations().get(i).getBlock_name());
                    }

                    list2 = contractorlocationmodel.getContractors();

                    for(int i = 0; i<list2.size(); i++ ){

                        contractorlist.add(contractorlocationmodel.getContractors().get(i).getFull_name());
                    }

                    ArrayAdapter adapter=new ArrayAdapter(CreateIndentActivity.this,android.R.layout.simple_list_item_1,location);
                    spinner_location.setAdapter(adapter);

                    ArrayAdapter adapte=new ArrayAdapter(CreateIndentActivity.this,android.R.layout.simple_list_item_1,contractorlist);
                    spinner_contractor_name.setAdapter(adapte);

                }else {
                    Toast.makeText(this, "Server busy", Toast.LENGTH_SHORT).show();

                }

        }
    }
}