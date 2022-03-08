package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IndividualIndentActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    int mMonth,mDay,mYear;
    Spinner sp_indi_contractorName,sp_indi_location,sp_indi_sublocation;
    EditText edt_indi_date,edt_indi_orderNumber,edt_indi_remarks;

    AppCompatButton btn_indi_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent);

        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();
        sp_indi_contractorName.setOnItemSelectedListener(OnCatSpinnerCL);
        sp_indi_location.setOnItemSelectedListener(OnCatSpinnerCL);
        sp_indi_sublocation.setOnItemSelectedListener(OnCatSpinnerCL);
    }

    private void initilize()
    {
        sp_indi_contractorName=findViewById(R.id.sp_indi_contractorName);

        sp_indi_location=findViewById(R.id.sp_indi_location);

        sp_indi_sublocation=findViewById(R.id.sp_indi_sublocation);
        edt_indi_date=findViewById(R.id.edt_indi_date);
        edt_indi_date.setOnClickListener(this);
        edt_indi_orderNumber=findViewById(R.id.edt_indi_orderNumber);
        edt_indi_remarks=findViewById(R.id.edt_indi_remarks);
        btn_indi_submit=findViewById(R.id.btn_indi_submit);
        btn_indi_submit.setOnClickListener(this);

        nav_home=findViewById(R.id.nav_home);
        nav_home.setOnClickListener(this);
        nav_boq_indent=findViewById(R.id.nav_boq_indent);
        nav_boq_indent.setOnClickListener(this);
        nav_Individual_indent=findViewById(R.id.nav_Individual_indent);
        nav_Individual_indent.setOnClickListener(this);
        nav_consumption=findViewById(R.id.nav_consumption);
        nav_consumption.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.nav_home:
                Intent hoIntent = new Intent(IndividualIndentActivity.this,MainActivity.class);
                startActivity(hoIntent);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent boIntent = new Intent(IndividualIndentActivity.this,IndentStatusActivity.class);
                startActivity(boIntent);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent Intentindi = new Intent(IndividualIndentActivity.this,IndividualIndentListActivity.class);
                startActivity(Intentindi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent Intentcon = new Intent(IndividualIndentActivity.this,ConsumptionListActivity.class);
                startActivity(Intentcon);
                finishAffinity();
                break;
            case R.id.btn_indi_submit:
                Intent intentsubmit = new Intent(IndividualIndentActivity.this,IndividualIndentMaterialActivity.class);
                startActivity(intentsubmit);
                break;

            case R.id.edt_indi_date:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(IndividualIndentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                Calendar calendar =Calendar.getInstance();
                                calendar.set(Calendar.MONTH,monthofyear);
                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                                edt_indi_date.setText(simpleDateFormat.format(calendar.getTime()));

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
}