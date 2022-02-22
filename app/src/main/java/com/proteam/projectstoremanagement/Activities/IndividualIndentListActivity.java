package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Adapters.IndividualIndentListAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.IndividualIndentListModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class IndividualIndentListActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView mToolbar;

    FloatingActionButton fab_add_individual_indent;
    ListView lv_individual_indent_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_indent_list);
        initialize();

        final ArrayList<IndividualIndentListModel> arrayList = new ArrayList<IndividualIndentListModel>();

        arrayList.add(new IndividualIndentListModel("1","Mondal Enterprises","Pending"));
        arrayList.add(new IndividualIndentListModel("2","Bright Energy","Pending"));
        arrayList.add(new IndividualIndentListModel("3","Shree Krishna Precast","Approved"));
        arrayList.add(new IndividualIndentListModel("4","Bright Energy","Rejected"));
        arrayList.add(new IndividualIndentListModel("5","Mondal Enterprises","Rejected"));
        arrayList.add(new IndividualIndentListModel("6","Shree Krishna Precast","Approved"));
        arrayList.add(new IndividualIndentListModel("7","Mondal Enterprises","Approved"));
        arrayList.add(new IndividualIndentListModel("8","Bright Energy","Pending"));
        arrayList.add(new IndividualIndentListModel("9","Mondal Enterprises","Rejected"));
        arrayList.add(new IndividualIndentListModel("10","Shree Krishna Precast","Rejected"));

        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        IndividualIndentListAdapter numbersArrayAdapter = new IndividualIndentListAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView indentStatusList = findViewById(R.id.lv_individual_indent_status);

        // set the numbersViewAdapter for ListView
        indentStatusList.setAdapter(numbersArrayAdapter);
    }

    private void initialize()
    {
        fab_add_individual_indent=findViewById(R.id.fab_add_individual_indent);
        fab_add_individual_indent.setOnClickListener(this);
        lv_individual_indent_status=findViewById(R.id.lv_individual_indent_status);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fab_add_individual_indent:
                Intent intent = new Intent(IndividualIndentListActivity.this,IndividualIndentActivity.class);
                startActivity(intent);
                break;
        }
    }
}