package com.proteam.projectstoremanagement.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.proteam.projectstoremanagement.Adapters.IndentStatusAdapter;
import com.proteam.projectstoremanagement.Adapters.UpdateIndentAdapter;
import com.proteam.projectstoremanagement.Model.IndentStatusModel;
import com.proteam.projectstoremanagement.Model.UpdateIndentModel;
import com.proteam.projectstoremanagement.R;

import java.util.ArrayList;

public class UpateIndentActivity extends AppCompatActivity {
    ImageView mToolbar;
    ListView lv_update_indent_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upate_indent);
        mToolbar = findViewById(R.id.back_toolbar);
        mToolbar.setOnClickListener(view -> onBackPressed());

        lv_update_indent_list=findViewById(R.id.lv_update_indent_list);



        final ArrayList<UpdateIndentModel> arrayList = new ArrayList<UpdateIndentModel>();

        arrayList.add(new UpdateIndentModel("1","Mondal Enterprises","454","6777"));
        arrayList.add(new UpdateIndentModel("2","Bright Energy","4545","5555"));
        arrayList.add(new UpdateIndentModel("3","Shree Krishna Precast","45455","59898"));


        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        UpdateIndentAdapter numbersArrayAdapter = new UpdateIndentAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView updateindentList = findViewById(R.id.lv_update_indent_list);

        // set the numbersViewAdapter for ListView
        updateindentList.setAdapter(numbersArrayAdapter);
    }
}