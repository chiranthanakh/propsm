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

public class CreateConsumptionActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    Spinner sp_con_contractorname,sp_con_location,sp_con_sublocation;
    EditText edt_con_date,edt_con_ordernum,edt_con_remarks;
    AppCompatButton btn_con_generate;
    int mMonth,mDay,mYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_consumption);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();
        sp_con_contractorname.setOnItemSelectedListener(OnCatSpinnerCL);
        sp_con_location.setOnItemSelectedListener(OnCatSpinnerCL);
        sp_con_sublocation.setOnItemSelectedListener(OnCatSpinnerCL);
    }


    private void initilize()
    {
        sp_con_contractorname=findViewById(R.id.sp_con_contractorname);
        sp_con_location=findViewById(R.id.sp_con_location);
        sp_con_sublocation=findViewById(R.id.sp_con_sublocation);

        edt_con_date=findViewById(R.id.edt_con_date);
        edt_con_date.setOnClickListener(this);
        edt_con_ordernum=findViewById(R.id.edt_con_ordernum);
        edt_con_remarks=findViewById(R.id.edt_con_remarks);

        btn_con_generate=findViewById(R.id.btn_con_generate);
        btn_con_generate.setOnClickListener(this);

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
                Intent intentHome = new Intent(CreateConsumptionActivity.this,MainActivity.class);
                startActivity(intentHome);
                finishAffinity();
            break;
            case R.id.nav_boq_indent:
                Intent intentboq = new Intent(CreateConsumptionActivity.this,IndentStatusActivity.class);
                startActivity(intentboq);
                finishAffinity();
            break;
            case R.id.nav_Individual_indent:
                Intent intentIndi = new Intent(CreateConsumptionActivity.this,IndividualIndentListActivity.class);
                startActivity(intentIndi);
                finishAffinity();
            break;
            case R.id.nav_consumption:
                Intent intentCList = new Intent(CreateConsumptionActivity.this,ConsumptionListActivity.class);
                startActivity(intentCList);
                finishAffinity();
                break;
            case R.id.btn_con_generate:
                Intent intentconsave = new Intent(CreateConsumptionActivity.this,ConsumptionMaterialActivity.class);
                startActivity(intentconsave);
                break;

            case R.id.edt_con_date:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateConsumptionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
                                Calendar calendar =Calendar.getInstance();
                                calendar.set(Calendar.MONTH,monthofyear);
                                calendar.set(Calendar.YEAR,year);
                                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
                                edt_con_date.setText(simpleDateFormat.format(calendar.getTime()));

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