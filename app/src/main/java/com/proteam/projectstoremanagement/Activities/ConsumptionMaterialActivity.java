package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.proteam.projectstoremanagement.R;

public class ConsumptionMaterialActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    BottomNavigationItemView nav_home,nav_boq_indent,nav_Individual_indent,nav_consumption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_material);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        initilize();
    }
    private void initilize()
    {
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
                Intent intenthome = new Intent(ConsumptionMaterialActivity.this,MainActivity.class);
                startActivity(intenthome);
                finishAffinity();
                break;
            case R.id.nav_boq_indent:
                Intent intent_boq = new Intent(ConsumptionMaterialActivity.this,IndentStatusActivity.class);
                startActivity(intent_boq);
                finishAffinity();
                break;
            case R.id.nav_Individual_indent:
                Intent intent_indi = new Intent(ConsumptionMaterialActivity.this,IndividualIndentListActivity.class);
                startActivity(intent_indi);
                finishAffinity();
                break;
            case R.id.nav_consumption:
                Intent intent_com = new Intent(ConsumptionMaterialActivity.this,ConsumptionListActivity.class);
                startActivity(intent_com);
                finishAffinity();
                break;
        }

    }
}