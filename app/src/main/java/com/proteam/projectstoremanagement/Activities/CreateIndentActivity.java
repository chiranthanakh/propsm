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

import com.proteam.projectstoremanagement.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateIndentActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    int mMonth,mDay,mYear;
    Spinner spinner_contractor_name, spinner_division_name, spinner_feeder_name;
    EditText edt_indent_date;

    AppCompatButton btn_indent_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_add);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());


        initilize();
        spinner_contractor_name.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner_division_name.setOnItemSelectedListener(OnCatSpinnerCL);
        spinner_feeder_name.setOnItemSelectedListener(OnCatSpinnerCL);
    }

    private void initilize() {
        spinner_contractor_name = findViewById(R.id.spinner_contractor_name);
        spinner_division_name = findViewById(R.id.spinner_division_name);
        spinner_feeder_name = findViewById(R.id.spinner_feeder_name);
        edt_indent_date = findViewById(R.id.edt_indent_date);
        edt_indent_date.setOnClickListener(this);
        btn_indent_generate=findViewById(R.id.btn_indent_generate);
        btn_indent_generate.setOnClickListener(this);


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


}