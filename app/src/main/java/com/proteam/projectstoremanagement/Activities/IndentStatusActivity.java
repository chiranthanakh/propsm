package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class IndentStatusActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mToolbar;
    DrawerLayout drawer_layout;

    FloatingActionButton fab_add_raise;
    ListView indent_status_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indent_status);
        mToolbar = findViewById(R.id.back_toolbar);

        initialize();

        final ArrayList<IndentStatusModel> arrayList = new ArrayList<IndentStatusModel>();

        arrayList.add(new IndentStatusModel("1","Mondal Enterprises","Pending"));
        arrayList.add(new IndentStatusModel("2","Bright Energy","Pending"));
        arrayList.add(new IndentStatusModel("3","Shree Krishna Precast","Approved"));
        arrayList.add(new IndentStatusModel("4","Bright Energy","Rejected"));
        arrayList.add(new IndentStatusModel("5","Mondal Enterprises","Rejected"));
        arrayList.add(new IndentStatusModel("6","Shree Krishna Precast","Approved"));
        arrayList.add(new IndentStatusModel("7","Mondal Enterprises","Approved"));
        arrayList.add(new IndentStatusModel("8","Bright Energy","Pending"));
        arrayList.add(new IndentStatusModel("9","Mondal Enterprises","Rejected"));
        arrayList.add(new IndentStatusModel("10","Shree Krishna Precast","Rejected"));
        arrayList.add(new IndentStatusModel("11","Mondal Enterprises","Approved"));

        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        IndentStatusAdapter numbersArrayAdapter = new IndentStatusAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView indentStatusList = findViewById(R.id.indent_status_listView);

        // set the numbersViewAdapter for ListView
        indentStatusList.setAdapter(numbersArrayAdapter);
    }

    private void initialize()
    {
        fab_add_raise=findViewById(R.id.fab_add_raise);
        fab_add_raise.setOnClickListener(this);
        indent_status_listView=findViewById(R.id.indent_status_listView);
        mToolbar.setOnClickListener(this);
        drawer_layout=findViewById(R.id.drawer_layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_add_raise:
                Intent intent = new Intent(IndentStatusActivity.this,CreateIndentActivity.class);
                startActivity(intent);
                break;
            case R.id.back_toolbar:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
    }
}